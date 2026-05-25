package com.codingpractice.ecommerce.service;

import com.codingpractice.ecommerce.dto.OrderDTO;
import com.codingpractice.ecommerce.dto.OrderItemDTO;
import com.codingpractice.ecommerce.exceptions.APIException;
import com.codingpractice.ecommerce.exceptions.ResourceNotFoundException;
import com.codingpractice.ecommerce.model.*;
import com.codingpractice.ecommerce.repository.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{


    private final CartRepository cartRepository;

    private final AddressRepository addressRepository;

    private final PaymentRepository paymentRepository;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final ProductRepository productRepository;

    private CartService cartService;
    @Autowired
    private ModelMapper modelMapper;

    public OrderServiceImpl(CartRepository cartRepository, AddressRepository addressRepository, PaymentRepository paymentRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public OrderDTO placeOrder(String emailId, Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage) {
        // getting User cart
        Cart cart = cartRepository.findCartByEmail(emailId);
        if(cart == null){
            throw new ResourceNotFoundException("Cart", "email", "emailId");
        }
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

        // create a new order with payment info
        Order order = new Order();
        order.setEmail(emailId);
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(cart.getTotalPrice());
        order.setOrderStatus("Order Accepted !");
        order.setAddress(address);
        Payment payment = new Payment(paymentMethod, pgPaymentId, pgStatus, pgResponseMessage, pgName);
        payment = paymentRepository.save(payment);
        order.setPayment(payment);
        Order savedOrder = orderRepository.save(order);

        // get items from the cart into the order items
        List<CartItem> cartItems = cart.getCartItems();
        if(cartItems.isEmpty()){
            throw new APIException("Cart is empty");
        }
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem cartItem: cartItems){
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setDiscount(cartItem.getDiscount());
            orderItem.setOrderedProductPrice(cartItem.getProductPrice());
            orderItem.setOrder(savedOrder);
            orderItems.add(orderItem);
        }
        List<OrderItem> savedOrderItems = orderItemRepository.saveAll(orderItems);
        // update product stack
        cart.getCartItems().forEach(item -> {
            int quantity = item.getQuantity();
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
            // clear the cart item
            cartService.deleteProductFromCart(cart.getCartId(), item.getProduct().getProductId());
        });

        // send back the order summary
        OrderDTO orderDTO = modelMapper.map(savedOrder, OrderDTO.class);
        orderItems.forEach(orderItem -> {
            orderDTO.getOrderItemDTOS().add(modelMapper.map(orderItem, OrderItemDTO.class));
        });
        orderDTO.setAddressId(addressId);
        return orderDTO;
    }
}

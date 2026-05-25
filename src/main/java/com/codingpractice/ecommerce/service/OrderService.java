package com.codingpractice.ecommerce.service;

import com.codingpractice.ecommerce.dto.OrderDTO;

public interface OrderService {
    OrderDTO placeOrder(String emailId, Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage);
}

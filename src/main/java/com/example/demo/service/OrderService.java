package com.example.demo.service;

import com.example.demo.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    String saveOrder(OrderDTO orderDTO);
    List<OrderDTO> getAllOrders();
}

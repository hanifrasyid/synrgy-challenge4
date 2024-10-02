package com.example.BinarFud.service;

import com.example.BinarFud.dto.OrderDetailEntityDTO;
import com.example.BinarFud.dto.OrderEntityDTO;
import com.example.BinarFud.entity.Order;
import com.example.BinarFud.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface OrderService {

    Page<Order> findAll(Pageable pageable);
    Map findById(UUID id);
    Map save(OrderEntityDTO orderDetail);
    Map update(UUID id, OrderEntityDTO orderDetail);

    Page<OrderDetail> findAllOrderDetail(Pageable pageable);

    Map findByIdOrderDetail(UUID id);

    Map saveOrderDetail(OrderDetailEntityDTO orderDetail);

    Map updateOrderDetail(UUID id, OrderDetailEntityDTO orderDetail);
}

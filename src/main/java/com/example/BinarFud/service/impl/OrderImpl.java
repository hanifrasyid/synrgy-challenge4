package com.example.BinarFud.service.impl;

import com.example.BinarFud.dto.OrderDetailEntityDTO;
import com.example.BinarFud.dto.OrderEntityDTO;
import com.example.BinarFud.entity.Order;
import com.example.BinarFud.entity.OrderDetail;
import com.example.BinarFud.repository.OrderDetailRepository;
import com.example.BinarFud.repository.OrderRepository;
import com.example.BinarFud.repository.ProductRepository;
import com.example.BinarFud.repository.UserRepository;
import com.example.BinarFud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class OrderImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Order> findAll(Pageable pageable) {
        log.debug("retrieve all merchant data from database");
        return orderRepository.findAll(pageable);
    }

    @Override
    public Map findById(UUID id) {
        Map<String, Object> map = new HashMap<>();

        Optional<Order> checkData = orderRepository.findById(id);

        if (checkData.isEmpty()) {
            map.put("status", 400);
            map.put("error", "data not found");
            log.error("didn't find Order data in Database");
        } else {
            // Add the status and data key-value pairs to the map.
            map.put("status", 200);
            map.put("data", checkData.get());
            log.info("Successfully find Order data in Database");
        }

        return map;
    }

    @Override
    public Map save(OrderEntityDTO order) {
        Map<String, Object> map = new HashMap<>();

        try{
            if(order.getOrder_time() != null &&
                    order.getDestination_address() != null &&
                    order.getUser_id() != null){
                Order transferData = new Order();
                transferData.setOrder_time(order.getOrder_time());
                transferData.setDestination_address(order.getDestination_address());
                if (userRepository.findById(order.getUser_id()).isPresent()){
                    transferData.setUser(userRepository.findById(order.getUser_id()).get());
                    map.put("status", "200");
                    map.put("data", orderRepository.save(transferData));
                    log.info("Successfully inserting new order data to Database");
                }else{
                    map.put("status", "400");
                    map.put("error", "cannot find related user");
                    log.error("cannot find related user");
                }

            }else{
                map.put("status", "400");
                map.put("error", "required parameter not satisfied");
                log.error("required parameter not satisfied");
            }

        }catch (Exception e){
            map.put("status", "400");
            map.put("error", e.getMessage());
            log.error(e.getMessage());
        }
        return map;
    }

    @Override
    public Map update(UUID id, OrderEntityDTO order) {
        Map<String, Object> map = new HashMap<>();

        Optional<Order> checkData = orderRepository.findById(id);
        if(checkData.isEmpty()){
            map.put("status", "400");
            map.put("error", "data not found.");
            log.error("didn't find Order data in Database");
        }else{
            Order result = checkData.get();
            if (order.getOrder_time() != null){
                result.setOrder_time(order.getOrder_time());
                log.debug("update order's order_time");
            }
            if (order.getDestination_address() != null){
                result.setDestination_address(order.getDestination_address());
                log.debug("update order's Destination_address");
            }
            if (order.getCompleted() != null){
                result.setCompleted(order.getCompleted());
                log.debug("update order's completed data");
            }
            if (order.getUser_id() != null){
                if (userRepository.findById(order.getUser_id()).isPresent()){
                    result.setUser(userRepository.findById(order.getUser_id()).get());
                }else{
                    map.put("status", "400");
                    map.put("error", "cannot find related user");
                    log.error("can't find related user data in database");
                    return map;
                }
            }
            map.put("status", "200");
            map.put("data", orderRepository.save(result));
            log.info("successfully updating order data to database");
        }
        return map;
    }

    @Override
    public Page<OrderDetail> findAllOrderDetail(Pageable pageable) {
        log.debug("retrieve all order detail data from database");
        return orderDetailRepository.findAll(pageable);
    }

    @Override
    public Map findByIdOrderDetail(UUID id) {
        Map<String, Object> map = new HashMap<>();

        Optional<OrderDetail> checkData = orderDetailRepository.findById(id);

        if (checkData.isEmpty()) {
            map.put("status", 400);
            map.put("error", "data not found");
            log.error("didn't find related order detail data in database");
        } else {
            // Add the status and data key-value pairs to the map.
            map.put("status", 200);
            map.put("data", checkData.get());
            log.info("successfully retrieve related order detail from database");
        }

        return map;
    }

    @Override
    public Map saveOrderDetail(OrderDetailEntityDTO orderDetail) {
        Map<String, Object> map = new HashMap<>();

        try{
            if(orderDetail.getOrder_id() != null &&
                    orderDetail.getProduct_id() != null &&
                    orderDetail.getQuantity() > 0 &&
                    orderDetail.getTotal_price() > 0){
                OrderDetail transferData = new OrderDetail();
                transferData.setQuantity(orderDetail.getQuantity());
                transferData.setTotal_price(orderDetail.getTotal_price());
                if (productRepository.findById(orderDetail.getProduct_id()).isPresent()){
                    transferData.setProduct(productRepository.findById(orderDetail.getProduct_id()).get());
                }else{
                    map.put("status", "400");
                    map.put("error", "cannot find related product");
                    log.error("cannot find related product in database");
                    return map;
                }
                if (orderRepository.findById(orderDetail.getOrder_id()).isPresent()){
                    transferData.setOrder(orderRepository.findById(orderDetail.getOrder_id()).get());
                    map.put("status", "200");
                    map.put("data", orderDetailRepository.save(transferData));
                    log.info("successfully inserting new order detail data to database");
                    return map;
                }else{
                    map.put("status", "400");
                    map.put("error", "cannot find related order");
                    log.error("didn't find related order in database");
                    return map;
                }

            }else{
                map.put("status", "400");
                map.put("error", "required parameter not satisfied");
                log.error("unsuccessful inserting new order detail to database");
            }

        }catch (Exception e){
            map.put("status", "400");
            map.put("error", e.getMessage());
            log.error("e.getMessage()");
        }
        return map;
    }

    @Override
    public Map updateOrderDetail(UUID id, OrderDetailEntityDTO orderDetail) {
        Map<String, Object> map = new HashMap<>();

        Optional<OrderDetail> checkData = orderDetailRepository.findById(id);
        if(checkData.isEmpty()){
            map.put("status", "400");
            map.put("error", "data not found.");
            log.error("unsuccessful updating order detail to database");

        }else{
            OrderDetail result = checkData.get();
            if (orderDetail.getQuantity() > 0){
                result.setQuantity(orderDetail.getQuantity());
            }
            if (orderDetail.getTotal_price() > 0){
                result.setTotal_price(orderDetail.getTotal_price());
            }
            if (orderDetail.getProduct_id() != null){
                if (productRepository.findById(orderDetail.getProduct_id()).isPresent()){
                    result.setProduct(productRepository.findById(orderDetail.getProduct_id()).get());
                }else{
                    map.put("status", "400");
                    map.put("error", "cannot find related product");
                    log.error("unsuccessful updating order detail to database");
                    return map;
                }
            }
            if (orderDetail.getOrder_id() != null){
                if (orderRepository.findById(orderDetail.getOrder_id()).isPresent()){
                    result.setOrder(orderRepository.findById(orderDetail.getOrder_id()).get());
                }else{
                    map.put("status", "400");
                    map.put("error", "cannot find related order");
                    log.error("unsuccessful updating order detail to database");
                    return map;
                }
            }
            map.put("status", "200");
            map.put("data", orderDetailRepository.save(result));
            log.error("unsuccessful updating order detail to database");
        }
        return map;
    }
}
package com.example.BinarFud.controller;

import com.example.BinarFud.dto.OrderEntityDTO;
import com.example.BinarFud.entity.Order;
import com.example.BinarFud.service.impl.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderImpl orderImpl;


    @GetMapping({"", "/"})
    public Page<Order> getOrder(){
        Pageable pageable = PageRequest.of(0, 100);
        return orderImpl.findAll(pageable);
    }

    @PostMapping({"", "/"})
    public Map addOrder(@RequestBody OrderEntityDTO order){

        return orderImpl.save(order);
    }

    @GetMapping({"{id}", "{id}/"})
    public Map findOrder(@PathVariable UUID id){
        return orderImpl.findById(id);
    }

    @PutMapping({"{id}", "{id}/"})
    public Map updateOrder(@PathVariable UUID id, @RequestBody OrderEntityDTO order){
        return orderImpl.update(id, order);
    }
}

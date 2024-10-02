package com.example.BinarFud.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderDetailEntityDTO {
    private UUID id;
    private UUID order_id;
    private UUID product_id;
    private int quantity;
    private int total_price;
}

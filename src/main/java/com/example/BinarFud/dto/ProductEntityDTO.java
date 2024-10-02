package com.example.BinarFud.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductEntityDTO {
    private UUID id;
    private String product_name;
    private int price;
    private Boolean available;
    private UUID Merchant_id;
}

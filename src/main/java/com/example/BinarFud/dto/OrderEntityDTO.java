package com.example.BinarFud.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class OrderEntityDTO {
    private UUID id;
    private Date order_time;
    private String destination_address;
    private Boolean completed;
    private UUID user_id;
}

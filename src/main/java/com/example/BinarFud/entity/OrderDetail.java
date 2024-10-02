package com.example.BinarFud.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "orderdetails")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne
    @JoinColumn
    Order order;

    @ManyToOne
    @JoinColumn
    Product product;

    int quantity;
    int total_price;
}

package com.example.BinarFud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String product_name;
    int price;
    Boolean available = true;

    @ManyToOne
    @JoinColumn
    Merchant merchant;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<OrderDetail> listOrderDetail = new ArrayList<OrderDetail>();
}

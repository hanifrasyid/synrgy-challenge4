package com.example.BinarFud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "merchants")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    String merchant_name;
    String merchant_location;
    @Column
    Boolean open = true;

    @JsonIgnore
    @OneToMany(mappedBy = "merchant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Product> products;
}

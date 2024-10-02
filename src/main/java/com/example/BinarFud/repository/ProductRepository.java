package com.example.BinarFud.repository;

import com.example.BinarFud.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query
    public Page<Product> findByAvailableTrue(Pageable pageable);
}

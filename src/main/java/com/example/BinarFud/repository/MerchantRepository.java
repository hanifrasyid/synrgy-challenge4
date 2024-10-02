package com.example.BinarFud.repository;

import com.example.BinarFud.entity.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {

    @Query
    public Page<Merchant> findByOpenTrue(Pageable pageable);
}

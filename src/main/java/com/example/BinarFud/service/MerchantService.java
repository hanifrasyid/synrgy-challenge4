package com.example.BinarFud.service;

import com.example.BinarFud.entity.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface MerchantService {

    Page<Merchant> findAll(Pageable pageable);

    Map save(Merchant merchant);

    Map updateAvailable(UUID id, Merchant merchant);

    Page<Merchant> findAvailable(Pageable pageable);

    Map findById(UUID id);
}

package com.example.BinarFud.service.impl;

import com.example.BinarFud.dto.ProductEntityDTO;
import com.example.BinarFud.entity.Product;
import com.example.BinarFud.repository.MerchantRepository;
import com.example.BinarFud.repository.ProductRepository;
import com.example.BinarFud.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        log.debug("retreieve all product data from database");
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findAvailable(Pageable pageable) {
        log.debug("retreieve all available product data from database");
        return productRepository.findByAvailableTrue(pageable);
    }

    @Override
    public Map findById(UUID id) {
        Map<String, Object> map = new HashMap<>();

        Optional<Product> checkData = productRepository.findById(id);

        if (checkData.isEmpty()) {
            map.put("status", 400);
            map.put("error", "data not found");
            log.error("unsuccessful find product data in database");
        } else {
            // Add the status and data key-value pairs to the map.
            map.put("status", 200);
            map.put("data", checkData.get());
            log.info("Successful find product data in database");
        }

        return map;
    }

    @Override
    public Map save(ProductEntityDTO product) {
        Map<String, Object> map = new HashMap<>();

        try{
            if(product.getProduct_name() != null &&
                    product.getPrice() > 0 &&
                    product.getMerchant_id() != null){
                Product transferData = new Product();
                transferData.setProduct_name(product.getProduct_name());
                transferData.setPrice(product.getPrice());
                if (merchantRepository.findById(product.getMerchant_id()).isPresent()){
                    transferData.setMerchant(merchantRepository.findById(product.getMerchant_id()).get());
                    map.put("status", "200");
                    map.put("data", productRepository.save(transferData));
                    log.info("Successful insert new product data to database");
                }else{
                    map.put("status", "400");
                    map.put("error", "cannot find related merchant");
                    log.error("unsuccessful find product data in database");
                }

            }else{
                map.put("status", "400");
                map.put("error", "required parameter not satisfied");
                log.error("unsuccessful find product data in database");
            }

        }catch (Exception e){
            map.put("status", "400");
            map.put("error", e.getMessage());
            log.error("unsuccessful find product data in database");
        }
        return map;
    }

    @Override
    public Map update(UUID id, ProductEntityDTO product) {
        Map<String, Object> map = new HashMap<>();

        Optional<Product> checkData = productRepository.findById(id);
        if(checkData.isEmpty()){
            map.put("status", "400");
            map.put("error", "data not found.");
            log.error("unsuccessful updating product data in database");
        }else{
            Product result = checkData.get();
            if (product.getProduct_name() != null){
                result.setProduct_name(product.getProduct_name());
            }
            if (product.getPrice() > 0){
                result.setPrice(product.getPrice());
            }
            if (product.getAvailable() != null){
                result.setAvailable(product.getAvailable());
            }
            if (product.getMerchant_id() != null){
                if (merchantRepository.findById(product.getMerchant_id()).isPresent()){
                    result.setMerchant(merchantRepository.findById(product.getMerchant_id()).get());
                }else{
                    map.put("status", "400");
                    map.put("error", "cannot find related merchant");
                    log.error("unsuccessful updating product data in database");
                    return map;
                }
            }
            map.put("status", "200");
            map.put("data", productRepository.save(result));
            log.info("Successful updating product data in database");
        }
        return map;
    }

    @Override
    public Map delete(UUID id) {
        Map<String, Object> map = new HashMap<>();

        Optional<Product> checkData = productRepository.findById(id);
        if(checkData.isEmpty()){
            map.put("status", "400");
            map.put("error", "data not found.");
            log.error("unsuccessful deleting product data from database");
        }else{
            productRepository.deleteById(id);
            map.put("status", "200");
            map.put("response", "user successfully deleted");
            log.info("Successful deleting product data from database");
        }
        return map;
    }
}


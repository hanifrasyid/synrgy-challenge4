package com.example.BinarFud.controller;

import com.example.BinarFud.dto.ProductEntityDTO;
import com.example.BinarFud.entity.Product;
import com.example.BinarFud.service.impl.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductImpl productImpl;

    @GetMapping({"", "/"})
    public Iterable<Product> getProduct(@RequestParam(defaultValue = "false") boolean available){
        Pageable pageable = PageRequest.of(0, 100);
        if (available){
            return productImpl.findAvailable(pageable);
        }else{
            return productImpl.findAll(pageable);
        }
    }

    @PostMapping({"", "/"})
    public Map addProduct(@RequestBody ProductEntityDTO product){

        return productImpl.save(product);
    }

    @GetMapping({"{id}", "{id}/"})
    public Map findProduct(@PathVariable UUID id){
        return productImpl.findById(id);
    }

    @PutMapping({"{id}", "{id}/"})
    public Map updateProduct(@PathVariable UUID id, @RequestBody ProductEntityDTO product){
        return productImpl.update(id, product);
    }

    @DeleteMapping({"{id}", "{id}/"})
    public Map deleteProduct(@PathVariable UUID id){
        return productImpl.delete(id);
    }
}

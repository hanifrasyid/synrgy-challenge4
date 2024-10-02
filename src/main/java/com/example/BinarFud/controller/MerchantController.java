package com.example.BinarFud.controller;

import com.example.BinarFud.entity.Merchant;
import com.example.BinarFud.service.impl.MerchantImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantImpl merchantImpl;

    @GetMapping({"", "/"})
    public Iterable<Merchant> getMerchant(@RequestParam(defaultValue = "false") boolean open){
        Pageable pageable = PageRequest.of(0, 100);
        if (open){
            return merchantImpl.findAvailable(pageable);
        }else{
            return merchantImpl.findAll(pageable);
        }
    }

    @PostMapping({"", "/"})
    public Map addMerchant(@RequestBody Merchant merchant){

        return merchantImpl.save(merchant);
    }

    @GetMapping({"{id}", "{id}/"})
    public Map findMerchant(@PathVariable UUID id){
        return merchantImpl.findById(id);
    }

    @PutMapping({"{id}", "{id}/"})
    public Map updateMerchant(@PathVariable UUID id, @RequestBody Merchant merchant){
        return merchantImpl.updateAvailable(id, merchant);
    }
}

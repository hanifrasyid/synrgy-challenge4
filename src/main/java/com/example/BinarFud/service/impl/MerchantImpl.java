package com.example.BinarFud.service.impl;

import com.example.BinarFud.entity.Merchant;
import com.example.BinarFud.repository.MerchantRepository;
import com.example.BinarFud.service.MerchantService;
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
public class MerchantImpl implements MerchantService {

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Page<Merchant> findAll(Pageable pageable) {
        log.debug("Retrieve all merchant data from database");
        return merchantRepository.findAll(pageable);
    }

    @Override
    public Map save(Merchant merchant) {
        Map<String, Object> map = new HashMap<>();

        try{
            if(merchant.getMerchant_name() != null ||
                    merchant.getMerchant_location() != null ||
                    merchant.getOpen() != null){
                Merchant result = merchantRepository.save(merchant);
                map.put("status", "200");
                map.put("data", result);
                log.info("Successfully inserting new merchant data to Database");
            }else{
                map.put("status", "400");
                map.put("error", "required parameter not satisfied");
                log.error("required parameter not satisfied");
            }

        }catch (Exception e){
            map.put("status", "400");
            map.put("error", e.getMessage());
            log.error(e.getMessage());
        }
        return map;
    }

    @Override
    public Map updateAvailable(UUID id, Merchant merchant) {
        Map<String, Object> map = new HashMap<>();

        Optional<Merchant> checkData = merchantRepository.findById(id);
        if(checkData.isEmpty()){
            map.put("status", "400");
            map.put("error", "data not found.");
            log.error("didn't find Merchant data in Database");
        }else if(merchant.getOpen() == null){
            map.put("status", "400");
            map.put("error", "'open' params not found");
            log.error("didn't 'open' params to update merchant availability");
        }else{
            Merchant result = checkData.get();
            result.setOpen(merchant.getOpen());
            map.put("status", "200");
            map.put("data", merchantRepository.save(result));
            log.info("Successfully update merchant data to Database");
        }
        return map;
    }

    @Override
    public Page<Merchant> findAvailable(Pageable pageable) {
        log.debug("retrieve merchant data from database that available");
        return merchantRepository.findByOpenTrue(pageable);
    }

    @Override
    public Map findById(UUID id) {
        Map<String, Object> map = new HashMap<>();

        Optional<Merchant> checkData= merchantRepository.findById(id);
        if (checkData.isEmpty()){
            map.put("error", "data not found");
            log.error("didn't find Merchant data in Database");
        }else{
            map.put("status", 200);
            map.put("data", checkData.get());
            log.info("Successfully find merchant data in Database");
        }
        return map;
    }
}

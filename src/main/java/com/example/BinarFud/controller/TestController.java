package com.example.BinarFud.controller;

import com.example.BinarFud.entity.User;
import com.example.BinarFud.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/generate-data")
    public Iterable<User> generateData(){
        log.debug("generate all data");
        User n = new User();
        n.setUsername("maxernest");
        n.setEmail_address("maxernest@gmail.com");
        n.setPassword("maxernest123");
        userRepository.save(n);
        return userRepository.findAll();
    }
    @GetMapping("*")
    public String unknownUrl(){
        log.debug("show all user data");
        return "Kalian nyasar, coba alamat url lain";
    }

}

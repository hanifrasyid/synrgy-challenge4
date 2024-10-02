package com.example.BinarFud.controller;

import com.example.BinarFud.entity.User;
import com.example.BinarFud.service.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserImpl userImpl;

    @GetMapping({"", "/"})
    public Page getUserData(){
        Pageable pageable = PageRequest.of(0, 100);
        return userImpl.findAll(pageable);
    }

    @PostMapping({"", "/"})
    public Map addUser(@RequestBody User user){
        return userImpl.save(user);
    }

    @GetMapping({"{id}", "{id}/"})
    public Map findUser(@PathVariable UUID id){
        return userImpl.findById(id);
    }

    @PutMapping({"{id}", "{id}/"})
    public Map updateUser(@PathVariable UUID id, @RequestBody User user){
        return userImpl.update(id, user);
    }

    @DeleteMapping({"{id}", "{id}/"})
    public Map deleteUser(@PathVariable UUID id){
        return userImpl.delete(id);
    }
}

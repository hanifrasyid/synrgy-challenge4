package com.example.BinarFud.service.impl;

import com.example.BinarFud.entity.User;
import com.example.BinarFud.repository.UserRepository;
import com.example.BinarFud.service.UserService;
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
public class UserImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        log.debug("retreieve all users data from database");
        return userRepository.findAll(pageable);
    }

    @Override
    public Map findById(UUID id) {
        Map<String, Object> map = new HashMap<>();

        Optional<User> checkData = userRepository.findById(id);

        if (checkData.isEmpty()) {
            map.put("status", 400);
            map.put("error", "data not found");
            log.error("unsuccessful find user data in database");
        } else {
            // Add the status and data key-value pairs to the map.
            map.put("status", 200);
            map.put("data", checkData.get());
            log.info("Successful find user data in database");
        }

        return map;
    }

    @Override
    public Map save(User user) {
        Map<String, Object> map = new HashMap<>();

        try{
            if(user.getUsername() != null &&
                    user.getEmail_address() != null &&
                    user.getPassword() != null){
                User result = userRepository.save(user);
                map.put("status", "200");
                map.put("data", result);
                log.info("Successful insert new user data to database");
            }else{
                map.put("status", "400");
                map.put("error", "required parameter not satisfied");
                log.error("Unsuccessful insert new user data to database");
            }

        }catch (Exception e){
            map.put("status", "400");
            map.put("error", e.getMessage());
            log.error("Unsuccessful insert new user data to database");
        }
        return map;
    }

    @Override
    public Map update(UUID id, User user) {
        Map<String, Object> map = new HashMap<>();

        Optional<User> checkData = userRepository.findById(id);
        if(checkData.isEmpty()){
            map.put("status", "400");
            map.put("error", "data not found.");
            log.error("Unsuccessful update user data in database");
        }else{
            User result = checkData.get();
            if (user.getUsername() != null){
                result.setUsername(user.getUsername());
            }
            if (user.getEmail_address() != null){
                result.setEmail_address(user.getEmail_address());
            }
            if (user.getPassword() != null){
                result.setPassword(user.getPassword());
            }
            map.put("status", "200");
            map.put("data", userRepository.save(result));
            log.info("Successful update new user data to database");
        }
        return map;
    }

    @Override
    public Map delete(UUID id) {
        Map<String, Object> map = new HashMap<>();

        Optional<User> checkData = userRepository.findById(id);
        if(checkData.isEmpty()){
            map.put("status", "400");
            map.put("error", "data not found.");
            log.error("Unsuccessful delete user data from database");
        }else{
            userRepository.deleteUser(id);
            map.put("status", "200");
            map.put("response", "user successfully deleted");
            log.error("Successful delete user data from database");
        }
        return map;
    }
}
package com.example.BinarFud.repository;

import com.example.BinarFud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Procedure("delete_user")
    void deleteUser(UUID id);
}

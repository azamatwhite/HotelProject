package com.example.hotelproject.repositories;

import com.example.hotelproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Аттар арқылы іздеу  логинға
    Optional<User> findByUsername(String username);

    // email үшін
    Optional<User> findByEmail(String email);

    // осындай email барма тексеру
    Boolean existsByEmail(String email);
    
    // осындай ат барма тексеру
    Boolean existsByUsername(String username);
}

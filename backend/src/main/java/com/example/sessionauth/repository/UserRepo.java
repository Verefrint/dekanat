package com.example.sessionauth.repository;

import com.example.sessionauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT e FROM User e WHERE e.email = :email")
    Optional<User> findByPrincipal(@Param(value = "email") String email);
}

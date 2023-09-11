package com.example.gr.repository;

import com.example.gr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User , Long> {

   @Query("select u from User u where u.userId = :id")
   User findUser(Long id);

   @Query("select u from User u where u.phoneNumber = :phoneNumber")
   User findUserByPhone(String phoneNumber);

   Optional<User> findByEmail(String email);

}

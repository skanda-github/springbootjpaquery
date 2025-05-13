package com.example.springbootjpaquery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springbootjpaquery.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    // Custom JPQL query to find by email
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);

    // Custom JPQL query to find users by name keyword (case-insensitive)
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<User> searchByNameContaining(String keyword);

    // Custom JPQL query to find users older than a given age
    @Query("SELECT u FROM User u WHERE u.age > ?1")
    List<User> findUsersOlderThan(int age);
}

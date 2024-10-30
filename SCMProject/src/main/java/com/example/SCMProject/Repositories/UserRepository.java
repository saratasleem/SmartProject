package com.example.SCMProject.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SCMProject.Entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
      Optional<User> findByEmail(String email);
}

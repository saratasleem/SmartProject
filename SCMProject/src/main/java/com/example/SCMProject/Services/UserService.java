package com.example.SCMProject.Services;

import java.util.List;
import java.util.Optional;

import com.example.SCMProject.Entities.User;
import com.example.SCMProject.Exception.ResourceNotFoundException;

public interface UserService {
      User saveUser(User user);
      Optional<User> getById(String userId);
      Optional<User> updateUser(User user) throws ResourceNotFoundException;
      void deleteUser(String id) throws ResourceNotFoundException;
      boolean isUserExist(String userId);
      boolean isUserExistByEmail(String email);
      List<User> getAllUser();
}

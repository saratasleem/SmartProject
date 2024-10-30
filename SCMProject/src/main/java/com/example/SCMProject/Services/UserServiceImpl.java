package com.example.SCMProject.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SCMProject.Entities.User;
import com.example.SCMProject.Exception.ResourceNotFoundException;
import com.example.SCMProject.Repositories.UserRepository;
import com.example.SCMProject.Utilities.AppConstants;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepo;

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        String userId=UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRolesList(List.of(AppConstants.ROLE_USER));
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getById(String userId) {
       
        return userRepo.findById(userId);
    }

    @Override
    public Optional<User> updateUser(User user) throws ResourceNotFoundException {
       User user2= userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
       user2.setName(user.getName());
       user2.setEmail(user.getEmail());
       user2.setPassword(user.getPassword());
       user2.setPhoneNumber(user.getPhoneNumber());
       user2.setAbout(user.getAbout());
       user2.setProfilePic(user.getProfilePic());
       user2.setEnabled(user.isEnabled());
       user2.setEmailVerified(user.isEmailVerified());
       user2.setPhoneVerified(user.isPhoneVerified());
       user2.setProvider(user.getProvider());
       user2.setProviderUserId(user.getProviderUserId());
       User save=userRepo.save(user2);
       return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) throws ResourceNotFoundException {
        User user2=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String userId) {
        User user2=userRepo.findById(userId).orElse(null);
        return user2!=null?true:false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
       User user2= userRepo.findByEmail(email).orElse(null);
         return user2!=null?true:false;
    }

    @Override
    public List<User> getAllUser() {
       return userRepo.findAll();
    }

}

package com.eray.expenseTrackingAPI.service;

import com.eray.expenseTrackingAPI.dto.*;
import com.eray.expenseTrackingAPI.dto.Request.CreateUserRequest;
import com.eray.expenseTrackingAPI.dto.Request.UpdateUserRequest;
import com.eray.expenseTrackingAPI.dto.converter.UserDtoConverter;
import com.eray.expenseTrackingAPI.exception.UserNotFoundException;
import com.eray.expenseTrackingAPI.model.User;

import com.eray.expenseTrackingAPI.repository.UserRepository;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> createUser(CreateUserRequest userRequest) {
        return null;
        }



    public UserDto updateUser(String tc, UpdateUserRequest updateUserRequest){
        User user = userRepository.findByTc(tc)
                .orElseThrow(() -> new UserNotFoundException("User not found by TC !"));
        User updatedUser = new User(
                user.getId(),
                updateUserRequest.getName(),
                updateUserRequest.getSurname(),
                user.getTc(),
                updateUserRequest.getPassword(),
                updateUserRequest.getCountry(),
                updateUserRequest.getCity(),
                updateUserRequest.getAddress(),
                user.getExpenses()
                );
      return   userDtoConverter.convert(userRepository.save(updatedUser));
    }

    public User findUserByTc(String tc) {
        return userRepository.findByTc(tc)
                .orElseThrow(() -> new UserNotFoundException("User not found by TC !"));
    }

    public UserDto findUserById(Long id){
        return userDtoConverter.convert(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found by id !")));
    }

    public void deleteUser(Long id){
        findUserById(id);
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
       return userRepository.findAll();

    }
}

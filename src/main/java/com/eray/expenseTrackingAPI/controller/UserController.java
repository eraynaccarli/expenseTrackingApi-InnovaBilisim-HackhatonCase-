package com.eray.expenseTrackingAPI.controller;

import com.eray.expenseTrackingAPI.dto.Request.UpdateUserRequest;
import com.eray.expenseTrackingAPI.dto.UserDto;
import com.eray.expenseTrackingAPI.model.User;
import com.eray.expenseTrackingAPI.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/expenseTracking/user")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/user/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/{tc}")
    public ResponseEntity<User> findUserByTc(@PathVariable("tc") String tc){
        return ResponseEntity.ok(userService.findUserByTc(tc));
    }


    @PutMapping("/admin/{tc}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("tc") String tc,@RequestBody UpdateUserRequest updateUserRequest){
        return ResponseEntity.ok(userService.updateUser(tc,updateUserRequest));
    }


    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}

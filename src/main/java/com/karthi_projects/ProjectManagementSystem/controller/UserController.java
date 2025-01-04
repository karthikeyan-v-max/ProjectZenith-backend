package com.karthi_projects.ProjectManagementSystem.controller;

import com.karthi_projects.ProjectManagementSystem.Model.User;
import com.karthi_projects.ProjectManagementSystem.repository.UserRepository;
import com.karthi_projects.ProjectManagementSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(
            @RequestHeader("Authorization") String token
    ) throws Exception {
        User user = userService.findUserProfileByJwt(token);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/updateprofile")
    public ResponseEntity<User> updateUserProfile(@RequestBody User updatedUser,@RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserProfileByJwt(token);
        user.setFullname(updatedUser.getFullname());
        if (updatedUser.getEmail() != null) {
            user.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            user.setPassword(updatedUser.getPassword());
        }
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser , HttpStatus.OK);
    }

}

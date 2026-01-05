package com.example.newproject.controller;

import com.example.newproject.entity.UserEntity;
import com.example.newproject.repository.UserEntryRepo;
import com.example.newproject.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserEntityController {

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private UserEntryRepo userEntryRepo;

    @PutMapping
    public ResponseEntity<?> updateLoggedInUser (@RequestBody UserEntity newEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();

        UserEntity oldEntity = userEntryService.getUserByName(loggedInUsername);

        if (oldEntity == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        oldEntity.setUsername(newEntity.getUsername());
        oldEntity.setPassword(newEntity.getPassword());
        userEntryService.saveUser(oldEntity);

        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(Principal principal){
        String username = principal.getName();
        userEntryRepo.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

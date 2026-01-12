package com.example.newproject.controller;

import com.example.newproject.entity.UserEntity;
import com.example.newproject.service.UserEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private UserEntryService userEntryService;


    @PostMapping("/create_user")
    public ResponseEntity<?> createUser(@RequestBody UserEntity user){
        userEntryService.saveNewUser(user);
        log.info("User saved: {}", user);
        return new ResponseEntity<>("User added successfully", HttpStatus.OK);
    }
}

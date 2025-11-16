package com.example.newproject.controller;


import com.example.newproject.entity.JournalEntity;
import com.example.newproject.entity.UserEntity;
import com.example.newproject.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntityController {

    @Autowired
    private UserEntryService userEntryService;

    @PostMapping
    public void createUser(@RequestBody UserEntity user){
        userEntryService.saveUser(user);
    }

    @GetMapping
    public List<UserEntity> getAllUser(){
        return userEntryService.getAllUser();
    }

    @GetMapping("/name/{username}")
    public UserEntity getUserByName(@PathVariable String username){
        return userEntryService.getUserByName(username);
    }

    @PutMapping("/name/{username}")
    public ResponseEntity<?> putEntityById(@PathVariable String username, @RequestBody UserEntity newEntity) {

        UserEntity oldEntity = userEntryService.getUserByName(username);

        if (oldEntity != null) {
            oldEntity.setUsername(newEntity.getUsername());
            oldEntity.setPassword(newEntity.getPassword());
            userEntryService.saveUser(oldEntity);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

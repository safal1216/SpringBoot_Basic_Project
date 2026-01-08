package com.example.newproject.controller;

import com.example.newproject.entity.UserEntity;
import com.example.newproject.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/fetch-users")
    public ResponseEntity<?> getALlUsers(){
        List<UserEntity>entityList = userEntryService.getAllUser();
        if(!entityList.isEmpty() && entityList!=null) {
            return new ResponseEntity<>(entityList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create-admin-user")
    public void createAdminUser(@RequestBody UserEntity user){
        userEntryService.saveAdminUser(user);
    }
}

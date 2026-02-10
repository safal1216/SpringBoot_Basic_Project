package com.example.newproject.controller;

import com.example.newproject.entity.UserEntity;
import com.example.newproject.service.UserDetailsServiceImpl;
import com.example.newproject.service.UserEntryService;
import com.example.newproject.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserEntity user){
        userEntryService.saveNewUser(user);
        log.info("User saved: {}", user);
        return new ResponseEntity<>("User added successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity user){
        try{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }catch (Exception e){
        log.error("Exception occurred while createAuthenticationToken ", e);
        return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
    }
    }



}

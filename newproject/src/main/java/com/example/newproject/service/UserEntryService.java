package com.example.newproject.service;

import com.example.newproject.entity.JournalEntity;
import com.example.newproject.entity.UserEntity;
import com.example.newproject.repository.UserEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepo userEntryRepo;

    public List<UserEntity> getAllUser() {
        return userEntryRepo.findAll();
    }

    public void saveUser(UserEntity userEntity) {
        userEntryRepo.save(userEntity);
    }

    public UserEntity getUserByName(String name) {
        return userEntryRepo.findByUsername(name);
    }

}




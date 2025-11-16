package com.example.newproject.repository;

import com.example.newproject.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserEntryRepo extends MongoRepository<UserEntity,String>{

    UserEntity findByUsername(String username);

}

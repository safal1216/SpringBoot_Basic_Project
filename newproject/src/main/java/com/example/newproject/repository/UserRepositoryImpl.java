package com.example.newproject.repository;

import com.example.newproject.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserEntity> getUsersForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is("saf"));
        query.addCriteria(Criteria.where("email").regex("'^[a-zA-Z0-9._%+-]+@gmail\\.con$'"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        List<UserEntity> list = mongoTemplate.find(query,UserEntity.class);
        return list;

    }


}
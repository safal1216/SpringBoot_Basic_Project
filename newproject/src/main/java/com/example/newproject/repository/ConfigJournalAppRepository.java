package com.example.newproject.repository;

import com.example.newproject.entity.ConfigJournalAppEntity;
import com.example.newproject.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {
}

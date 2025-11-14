package com.example.newproject.service;

import com.example.newproject.entity.JournalEntity;
import com.example.newproject.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntity entity){
        journalEntryRepo.save(entity);
    }

    public List<JournalEntity> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntity> getByID(ObjectId ID){
        return journalEntryRepo.findById(ID);
    }

    public void deleteByID(ObjectId ID){
        journalEntryRepo.deleteById(ID);
    }



}

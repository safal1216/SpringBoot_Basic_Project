package com.example.newproject.service;

import com.example.newproject.entity.JournalEntity;
import com.example.newproject.entity.UserEntity;
import com.example.newproject.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserEntryService userEntryService;

    public void saveEntry(JournalEntity entity , String username){
        UserEntity user = userEntryService.getUserByName(username);
        entity.setDate(LocalDateTime.now());
        JournalEntity save = journalEntryRepo.save(entity);
        user.getJournalEntityList().add(save);
        userEntryService.saveUser(user);

    }
    public void saveEntry(JournalEntity entity){
        journalEntryRepo.save(entity);
    }

    public List<JournalEntity> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntity> getByID(ObjectId ID){
        return journalEntryRepo.findById(ID);
    }

    public void deleteByID(ObjectId ID ,String username){
        UserEntity user = userEntryService.getUserByName(username);
        user.getJournalEntityList().removeIf(x->x.getId().equals(ID));
        userEntryService.saveUser(user);
        journalEntryRepo.deleteById(ID);
    }



}

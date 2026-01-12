package com.example.newproject.service;

import com.example.newproject.entity.JournalEntity;
import com.example.newproject.entity.UserEntity;
import com.example.newproject.repository.JournalEntryRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserEntryService userEntryService;

    @Transactional
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

    public boolean deleteByID(ObjectId ID ,String username){
        boolean removed = false;
        try {
            UserEntity user = userEntryService.getUserByName(username);
            removed = user.getJournalEntityList().removeIf(x -> x.getId().equals(ID));
            if (removed) {
                userEntryService.saveUser(user);
                journalEntryRepo.deleteById(ID);
            }
        }
        catch (Exception e){
            log.debug("asxasx");
            throw new RuntimeException("some error is coming up");

        }
        return removed;
    }



}

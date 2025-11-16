package com.example.newproject.controller;
import com.example.newproject.entity.JournalEntity;
import com.example.newproject.entity.UserEntity;
import com.example.newproject.service.JournalEntryService;
import com.example.newproject.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;

    @PostMapping("{username}")
    public ResponseEntity<?> createEntity(@RequestBody JournalEntity journalEntity,@PathVariable String username ){
        try{
            UserEntity user = userEntryService.getUserByName(username);
            journalEntryService.saveEntry(journalEntity,username);
            return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{username}")
    public ResponseEntity<?> getALlJournalEntriesByUser(@PathVariable String username){
        UserEntity user = userEntryService.getUserByName(username);
        ArrayList<JournalEntity> list = user.getJournalEntityList();
        if(list!=null && !list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id/{ID}")
    public ResponseEntity<?> getEntityById(@PathVariable ObjectId ID){
        Optional<JournalEntity>journal =   journalEntryService.getByID(ID);
        if(journal.isPresent()){
            return new ResponseEntity<>(journal.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id/{username}/{ID}")
    public ResponseEntity<?> deleteEntityById(@PathVariable ObjectId ID,@PathVariable String username ){
        journalEntryService.deleteByID(ID,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{username}/{ID}")
    public ResponseEntity<?> putEntityById(@PathVariable ObjectId ID, @RequestBody JournalEntity newEntity,@PathVariable String username ) {

        JournalEntity oldEntity = journalEntryService.getByID(ID)
                .orElseThrow(() -> new RuntimeException("Entity not found"));

        if (newEntity.getTitle() != null && !newEntity.getTitle().isBlank()) {
            oldEntity.setTitle(newEntity.getTitle());
        }

        if (newEntity.getDetail() != null && !newEntity.getDetail().isBlank()) {
            oldEntity.setDetail(newEntity.getDetail());
        }
        journalEntryService.saveEntry(oldEntity);

        if (oldEntity != null) {
            return new ResponseEntity<>(oldEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

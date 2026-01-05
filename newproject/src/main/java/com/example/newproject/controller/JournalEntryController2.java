package com.example.newproject.controller;
import com.example.newproject.entity.JournalEntity;
import com.example.newproject.entity.UserEntity;
import com.example.newproject.service.JournalEntryService;
import com.example.newproject.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;

    @PostMapping
    public ResponseEntity<?> createEntity(@RequestBody JournalEntity journalEntity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        try{
            UserEntity user = userEntryService.getUserByName(loggedInUsername);
            journalEntryService.saveEntry(journalEntity,loggedInUsername);
            return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getALlJournalEntriesByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        UserEntity user = userEntryService.getUserByName(loggedInUsername);
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        UserEntity user = userEntryService.getUserByName(loggedInUsername);
        List<JournalEntity> journalEntityList =  user.getJournalEntityList().stream().filter(x->x.getId().equals(ID)).toList();
        if(!journalEntityList.isEmpty()){
            Optional<JournalEntity>entity = journalEntryService.getByID(ID);
            if(entity.isPresent()) {
                return new ResponseEntity<>(entity.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{ID}")
    public ResponseEntity<?> deleteEntityById(@PathVariable ObjectId ID ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        boolean removed = journalEntryService.deleteByID(ID, loggedInUsername);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/id/{ID}")
    public ResponseEntity<?> putEntityById(@PathVariable ObjectId ID, @RequestBody JournalEntity newEntity ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        UserEntity user = userEntryService.getUserByName(loggedInUsername);
        List<JournalEntity> journalEntityList =  user.getJournalEntityList().stream().filter(x->x.getId().equals(ID)).toList();

        if(!journalEntityList.isEmpty()) {
            JournalEntity oldEntity = journalEntryService.getByID(ID)
                    .orElseThrow(() -> new RuntimeException("Entity not found"));

            if (newEntity.getTitle() != null && !newEntity.getTitle().isBlank()) {
                oldEntity.setTitle(newEntity.getTitle());
            }

            if (newEntity.getDetail() != null && !newEntity.getDetail().isBlank()) {
                oldEntity.setDetail(newEntity.getDetail());
            }
            journalEntryService.saveEntry(oldEntity);

            return new ResponseEntity<>(oldEntity, HttpStatus.OK);
            }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
}

package com.example.newproject.controller;

import com.example.newproject.entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    public Map<Long, JournalEntity> mp = new HashMap<>();

    @PostMapping("/postentity")
    public boolean createEntity(@RequestBody JournalEntity journalEntity ){
        mp.put(journalEntity.getId(),journalEntity);
        return true;
    }

    @GetMapping
    public List<JournalEntity> getALl(){
        return new ArrayList<>(mp.values());
    }

    @GetMapping("/id/{ID}")
    public JournalEntity getEntityById(@PathVariable Long ID){
       JournalEntity journal =  mp.get(ID);
       return journal;
    }

    @DeleteMapping("/id/{ID}")
    public boolean deleteEntityById(@PathVariable Long ID){
        mp.remove(ID);
        return true;
    }

    @PutMapping("/id/{ID}")
    public JournalEntity putEntityById(@PathVariable Long ID, @RequestBody JournalEntity entity){
        return mp.put(ID, entity);
    }







}

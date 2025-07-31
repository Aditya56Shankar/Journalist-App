package com.aditya_shankar.journalApp.controller;

import com.aditya_shankar.journalApp.Entity.JournalEntry;
import com.aditya_shankar.journalApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {


    @Autowired
    private JournalEntryService journalEntryService;
    public ResponseEntity<?> getAll(){
        List<JournalEntry>all=journalEntryService.getAll();
        if(all!=null) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry){
        journalEntryService.saveEntry(journalEntry);
        return new ResponseEntity<>(journalEntry,HttpStatus.OK);
    }



    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id){
        Optional<JournalEntry> journalEntry= journalEntryService.findById(id);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id){
        journalEntryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntry> updateid(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry){
        JournalEntry old =journalEntryService.findById(id).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null&& !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
            return new ResponseEntity<>(newEntry,HttpStatus.OK);
        }
        journalEntryService.saveEntry(old);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}

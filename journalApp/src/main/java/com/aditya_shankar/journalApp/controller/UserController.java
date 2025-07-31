package com.aditya_shankar.journalApp.controller;

import com.aditya_shankar.journalApp.Entity.JournalEntry;
import com.aditya_shankar.journalApp.Entity.User;
import com.aditya_shankar.journalApp.services.JournalEntryService;
import com.aditya_shankar.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    public List<User> getAll(){
        return userService.getAll();
    }



    @PostMapping
    public void createEntry(@RequestBody User user){
        userService.saveUser(user);
    }



//    @GetMapping
//    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id){
//
//    }
//
//
//
//    @DeleteMapping
//    public ResponseEntity<?> deleteById(@PathVariable ObjectId id){
//
//    }


    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user){
        User userIn=userService.findByUserName(user.getUsername());

        if(userIn!=null){
            userIn.setUsername(userIn.getUsername());
            userIn.setPass(userIn.getPass());
            userService.saveUser(userIn);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

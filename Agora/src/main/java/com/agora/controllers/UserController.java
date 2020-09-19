package com.agora.controllers;
import com.agora.Filters.CorsFilter;
import com.agora.services.HashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agora.models.User;
import com.agora.services.UserService;

import javax.persistence.EntityListeners;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("user")

public class UserController {

    private UserService service;
    private HashingService hashingService;


    @Autowired
    public UserController(UserService userService, HashingService hashingService) {
        this.service = userService;
        this.hashingService = hashingService;
    }

    @CrossOrigin
    @GetMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<Set<User>> findAll() {
        Set<User> result = service.findAll();
        if(result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

    @CrossOrigin
    @GetMapping(value = "{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<User> findById(@PathVariable String id) {
        User result = service.findUserById(Integer.parseInt(id));
        if(result == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

    @CrossOrigin
    @PostMapping
    @ResponseBody
    public ResponseEntity<User> insert(@RequestBody User user) {
        if(user.getId() != 0) {
            return ResponseEntity.badRequest().build();
        }

//        user.setPassword(hashingService.hashPassword(user.getPassword()));

        service.save(user);

        if(user.getId() == 0) {
            // Failed to insert properly
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.accepted().body(user);
    }

    @CrossOrigin
    @DeleteMapping(value = "{id}")
    @ResponseBody
    public ResponseEntity<Boolean> delete(@PathVariable String id){

        User user = service.findUserById(Integer.parseInt(id));

        if(user == null){
            return ResponseEntity.badRequest().build();
        }

        service.delete(user);

        return ResponseEntity.accepted().body(true);
    }
}
package com.agora.controllers;
import org.springframework.beans.factory.annotation.Autowired;
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


@Controller
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService userService) {
        this.service = userService;
    }


    @GetMapping(path = "user", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Set<User>> findAll() {
        Set<User> result = service.findAll();
        if(result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }


    @PostMapping("user")
    @ResponseBody
    public ResponseEntity<User> insert(@RequestBody User user) {
        if(user.getId() != 0) {
            return ResponseEntity.badRequest().build();
        }

        service.save(user);

        if(user.getId() == 0) {
            // Failed to insert properly
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.accepted().body(user);
    }
}
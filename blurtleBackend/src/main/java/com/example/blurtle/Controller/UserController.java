package com.example.blurtle.Controller;

import com.example.blurtle.RequestDTO.CreateUserDTO;
import com.example.blurtle.Service.UserService;
import jakarta.validation.Valid;
import com.example.blurtle.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Blurtle/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody CreateUserDTO user) {
        try{
            User newUser = userService.addUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}

package com.example.blurtle.Controller;

import com.example.blurtle.RequestDTO.CreateUserDTO;
import com.example.blurtle.ResponseDTO.UserResponseDTO;
import com.example.blurtle.Service.UserService;
import jakarta.validation.Valid;
import com.example.blurtle.Entity.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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
            UserResponseDTO newUser = userService.addUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(required = true) Long id){
        try{
            UserResponseDTO user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}

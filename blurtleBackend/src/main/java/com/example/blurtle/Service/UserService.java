package com.example.blurtle.Service;

import com.example.blurtle.Entity.User;
import com.example.blurtle.Repository.PuzzleRepo;
import com.example.blurtle.Repository.UserRepo;
import com.example.blurtle.RequestDTO.CreateUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    Logger log = LoggerFactory.getLogger(UserService.class);


    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /*
     * addUser
     * Input: User object
     * Returns: User object
     * Description: Method to save User object to database.
     * Throws and catches DataAccessException on failure
     */
    public User addUser(CreateUserDTO request){

        User user = new User(
                request.firstName(),
                request.lastName(),
                request.email()
        );

        try{
            User inputUser = userRepo.save(user);
            log.info("User: {} saved successfully", inputUser);
            return inputUser;
        }
        catch(DataIntegrityViolationException e){
            log.warn("Failed to save user", e);
            throw e;
        }
    }
}

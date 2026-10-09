package com.example.blurtle.Service;

import com.example.blurtle.Entity.User;
import com.example.blurtle.Repository.PuzzleRepo;
import com.example.blurtle.Repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
     * Returns: void
     * Description: Method to save User object to database.
     * Throws and catches DataAccessException on failure
     */
    public User addUser(User user){
        try{
            User inputUser = userRepo.save(user);
            log.info("User: " + inputUser.toString() + " saved successfully");
            return inputUser;
        }
        catch(DataAccessException e){
            log.error("Failed to save user", e);
            throw e;
        }
    }
}

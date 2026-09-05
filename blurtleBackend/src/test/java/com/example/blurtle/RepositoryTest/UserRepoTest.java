package com.example.blurtle.RepositoryTest;

import com.example.blurtle.Entity.User;
import com.example.blurtle.Repository.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    private User testUser;

    @BeforeEach
    public void setup() {
        testUser = new User("testFirstName", "testLastName", "testEmail@gmail.com");
        userRepo.save(testUser);
    }

    @AfterEach
    public void tearDown() {
        userRepo.deleteAll();
    }

    @Test
    void givenUser_whenSaved_thenCanBeFoundById() {
        User savedUser = userRepo.findById(testUser.getUserId()).orElse(null);
        assertNotNull(savedUser);
        assertEquals(testUser.getFirstName(), savedUser.getFirstName());
        assertEquals(testUser.getEmail(), savedUser.getEmail());
    }
}

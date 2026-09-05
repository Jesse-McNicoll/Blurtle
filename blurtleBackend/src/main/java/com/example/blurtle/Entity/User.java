package com.example.blurtle.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Long longestStreak;

    @Column(nullable = false)
    private Long currentStreak;

    @Column(nullable = true)
    private LocalDate lastSolved;

    public User(){
        //Explicitly setting default values
        longestStreak = 0L;
        currentStreak = 0L;
        //Localdate is omitted, as null values are allowed until the user has solved their first puzzle
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        longestStreak = 0L;
        currentStreak = 0L;
        //Again, omitting local date until confirmation of a first solved puzzle.
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", longestStreak=" + longestStreak +
                ", currentStreak=" + currentStreak +
                ", lastSolved=" + lastSolved +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getLongestStreak() {
        return longestStreak;
    }

    public void setLongestStreak(Long longestStreak) {
        this.longestStreak = longestStreak;
    }

    public Long getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(Long currentStreak) {
        this.currentStreak = currentStreak;
    }

    public LocalDate getLastSolved() {
        return lastSolved;
    }

    public void setLastSolved(LocalDate lastSolved) {
        this.lastSolved = lastSolved;
    }
}

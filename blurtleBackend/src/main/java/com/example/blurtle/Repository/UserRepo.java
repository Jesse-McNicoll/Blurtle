package com.example.blurtle.Repository;

import com.example.blurtle.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {


}

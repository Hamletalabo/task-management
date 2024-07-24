package org.hamlet.taskmgt.utils;

import lombok.Data;
import org.hamlet.taskmgt.model.entity.UserEntity;
import org.hamlet.taskmgt.repository.UserRepository;

import java.util.Optional;


public class UserUtils {

    private UserRepository user;


    public  boolean checkUser (String email){
       Optional<UserEntity> optionalUser =  user.findByEmail(email);
       if (optionalUser.isEmpty()){
           throw new RuntimeException();
           //we can add a redirect to the login page
       }
        return true;
    }
}

package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository usersRepository;


    public boolean authenticate(String username, String password) {
        String user = usersRepository.checkUserInput(username,password);
        if (user != null && user.equals(password)) {
            return true;
        }
        return false;
    }
}

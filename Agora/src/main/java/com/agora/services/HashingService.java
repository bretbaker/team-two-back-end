package com.agora.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.agora.models.User;
import org.springframework.stereotype.Service;

import javax.persistence.PrePersist;

@Service
public class HashingService {


    public HashingService() {
        super();
    }

    public String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public boolean checkHashedPassword(String given_password, String curr_password) {
        if(BCrypt.verifyer().verify(given_password.toCharArray(), curr_password).verified) {
            return true;
        }
        return false;
    }

    @PrePersist
    public void setHashedPassword(User u) {
        String curr_password = u.getPassword();

        u.setPassword(hashPassword(curr_password));
    }

}

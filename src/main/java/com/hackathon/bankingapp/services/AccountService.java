package com.hackathon.bankingapp.services;

import com.hackathon.bankingapp.models.User;
import com.hackathon.bankingapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private IUserRepository iUserRepository;

    public boolean createPin(Long userId, String newPin) {
        User user = iUserRepository.findById(userId).orElse(null);
        if (user != null && user.getPin() == null) {
            user.setPin(newPin);
            iUserRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean updatePin(Long userId, String oldPin, String newPin) {
        User user = iUserRepository.findById(userId).orElse(null);
        if (user != null && user.getPin().equals(oldPin)) {
            user.setPin(newPin);
            iUserRepository.save(user);
            return true;
        }
        return false;
    }

}

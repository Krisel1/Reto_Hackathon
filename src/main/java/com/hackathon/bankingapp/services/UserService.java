package com.hackathon.bankingapp.services;

import com.hackathon.bankingapp.models.User;
import com.hackathon.bankingapp.repositories.IUserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private final IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public ArrayList<User> getAllUsers(){
        return(ArrayList<User>) iUserRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return iUserRepository.findById(id);
    }

    public User createUser(User newUser){
        return iUserRepository.save(newUser);
    }

    public void updateUser(User user){
        iUserRepository.save(user);
    }

    public String deleteUser(Long id){
        try{
            iUserRepository.deleteById(id);
            return "User has been deleted";
        }catch (Exception error){
            return "User not found";
        }
    }
}

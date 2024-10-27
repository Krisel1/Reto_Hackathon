package com.hackathon.bankingapp.controllers;

import com.hackathon.bankingapp.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account/pin")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("")
    public ResponseEntity<String> createPin(@RequestParam Long userId, @RequestParam String newPin) {
        boolean isCreated = accountService.createPin(userId, newPin);
        if (isCreated) {
            return new ResponseEntity<>("PIN created successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Could not create PIN.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("")
    public ResponseEntity<String> updatePin(@RequestParam Long userId, @RequestParam String oldPin, @RequestParam String newPin) {
        boolean isUpdated = accountService.updatePin(userId, oldPin, newPin);
        if (isUpdated) {
            return new ResponseEntity<>("PIN successfully updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not update PIN. Verify the current PIN.", HttpStatus.BAD_REQUEST);
        }
    }
}

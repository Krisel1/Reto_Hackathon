package com.hackathon.bankingapp.controllers;

import com.hackathon.bankingapp.models.Transaction;
import com.hackathon.bankingapp.services.RoboAdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/robo-advisors")
public class RoboAdvisorController {

    @Autowired
    private RoboAdvisorService roboAdvisorService;

    @PostMapping("/trade")
    public ResponseEntity<Transaction> performAutomatedTrade(@RequestParam Long userId, @RequestParam String stockSymbol) {
        Transaction transaction = roboAdvisorService.automatedTrade(userId, stockSymbol);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}

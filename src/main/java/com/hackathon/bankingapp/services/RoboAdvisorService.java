package com.hackathon.bankingapp.services;

import com.hackathon.bankingapp.models.Transaction;
import com.hackathon.bankingapp.repositories.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class RoboAdvisorService {

    @Autowired
    private ITransactionRepository iTransactionRepository;

    public Transaction automatedTrade(Long userId, String stockSymbol) {
        Random random = new Random();
        double simulatedAmount = 100.0 + (500.0 * random.nextDouble());
        String type = random.nextBoolean() ? "BUY" : "SELL";

        Transaction transaction = new Transaction();
        transaction.setAmount(simulatedAmount);
        transaction.setType(type);
        transaction.setDate(LocalDateTime.now());

        return iTransactionRepository.save(transaction);

    }

}

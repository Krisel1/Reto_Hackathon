package com.hackathon.bankingapp.services;

import com.hackathon.bankingapp.exceptions.ResourceNotFoundException;
import com.hackathon.bankingapp.models.Transaction;
import com.hackathon.bankingapp.repositories.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private ITransactionRepository iTransactionRepository;

    public List<Transaction> getTransactionsByUserId(Long userId) {
        return iTransactionRepository.findByUserId(userId);
    }

    public Transaction getTransactionById(Long id) {
        return iTransactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
    }

    public Transaction createTransaction(Transaction transaction) {
        return iTransactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        Transaction transaction = iTransactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
        transaction.setAmount(transactionDetails.getAmount());
        transaction.setType(transactionDetails.getType());
        transaction.setDate(transactionDetails.getDate());
        return iTransactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        iTransactionRepository.deleteById(id);
    }

}

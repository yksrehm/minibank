package com.mherscode.minibank.services;

import com.mherscode.minibank.model.Account;
import com.mherscode.minibank.model.Transaction;
import com.mherscode.minibank.model.enums.TransType;
import com.mherscode.minibank.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public void save(Account account, double amount, String description, TransType type) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setAccount(account);
        transaction.setAvailableBalance(account.getAcctBalance());
        transaction.setDescription(description);
        transaction.setType(type);
        transactionRepository.save(transaction);
    }

    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    }

}

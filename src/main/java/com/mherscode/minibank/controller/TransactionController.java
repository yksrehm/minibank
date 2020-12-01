package com.mherscode.minibank.controller;

import com.mherscode.minibank.model.Transaction;
import com.mherscode.minibank.model.dto.GetRequestDto;
import com.mherscode.minibank.services.AccountService;
import com.mherscode.minibank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/transaction")
    public String home(Map<String, Object> model) {
        model.put("transactionList", transactionService.getAll());
        return "transaction";
    }

    @PostMapping("/api/save-account")
    public ResponseEntity<String> saveAccount(@RequestBody GetRequestDto dto){
        if ("success".equals(accountService.saveAccount(dto.getUsername()))) {
            return ResponseEntity.ok().body("Account saved.");
        }
        return ResponseEntity.badRequest().body("Unable to save account.");
    }

    @PostMapping("/api/transactions")
    public ResponseEntity<List<Transaction>> getAllTrans(){
        return ResponseEntity.ok(transactionService.getAll());
    }


    @PostMapping("/api/deposit")
    public ResponseEntity<String>  deposit(@RequestBody GetRequestDto dto) {
        if ("success".equals(accountService.deposit(dto.getAmount(), dto.getUsername()))) {
            return ResponseEntity.ok().body("success deposit!");
        }
        return ResponseEntity.badRequest().body("failed deposit!");
    }

    @PostMapping("/api/withdraw")
    public ResponseEntity<String>  withdraw(@RequestBody GetRequestDto dto) {
        if ("success".equals(accountService.withdraw(dto.getAmount(), dto.getUsername()))) {
            return ResponseEntity.ok().body("success withdraw!");
        }
        return ResponseEntity.badRequest().body("failed withdraw!");
    }

    @PostMapping("/api/transfer")
    public ResponseEntity<String> transfer(@RequestBody GetRequestDto dto) {
        if ("success".equals(accountService.transfer(dto.getAmount(), dto.getUsername(), dto.getRcpUserName()))) {
            return ResponseEntity.ok().body("success transfer!");
        }
        return ResponseEntity.badRequest().body("failed transfer!");
    }

}

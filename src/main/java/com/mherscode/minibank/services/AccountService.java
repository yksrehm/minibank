package com.mherscode.minibank.services;

import com.mherscode.minibank.model.Account;
import com.mherscode.minibank.model.User;
import com.mherscode.minibank.model.enums.TransType;
import com.mherscode.minibank.repositories.AccountRepository;
import com.mherscode.minibank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionService transactionService;


    public String saveAccount(String username){
        User user = userRepository.findByUsername(username);
        Optional<Account> account = accountRepository.findByUser(username);
        Account acct = account.orElseGet(Account::new);
        acct.setAcctNo(generateAcctNo());
        acct.setAcctBalance(new BigDecimal(0.0));
        if (user != null) {
            acct.setUser(user);
            accountRepository.save(acct);
            return "success";
        }
        return "failed";
    }

    public String deposit(double amount, String username) {
        Account account = accountRepository.findByUserName(username);
        if (account != null) {
            account.setAcctBalance(account.getAcctBalance().add(new BigDecimal(amount)));
            accountRepository.save(account);
            transactionService.save(account, amount, "Deposit to account.", TransType.DEPOSIT);
            return "success";
        }
        return "failed";
    }

    public String withdraw(double amount, String username) {
        Account account = accountRepository.findByUserName(username);
        if (account != null) {
            account.setAcctBalance(account.getAcctBalance().subtract(new BigDecimal(amount)));
            accountRepository.save(account);
            transactionService.save(account, amount, "Withdraw from account.", TransType.WITHDRAW);
            return "success";
        }
        return "failed";
    }

    public String transfer(double amount, String sendUsrName, String rcpUsrName){
        Account account = accountRepository.findByUserName(sendUsrName);
        Account rcpAcct = accountRepository.findByUserName(rcpUsrName);

        if (account != null && rcpAcct != null) {
            account.setAcctBalance(account.getAcctBalance().subtract(new BigDecimal(amount)));
            accountRepository.save(account);

            rcpAcct.setAcctBalance(rcpAcct.getAcctBalance().add(new BigDecimal(amount)));
            accountRepository.save(rcpAcct);
            transactionService.save(account, amount,
                    "Between account transfer from " + account.getAcctNo() + " to " + rcpAcct.getAcctNo(),
                    TransType.TRANSFER);
            return "success";
        }
        return "failed";
    }


    private String generateAcctNo() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(random.nextInt(9) + 1);
        IntStream.range(0, 11).map(i -> random.nextInt(10)).forEach(sb::append);
        return sb.toString();
    }
}

package com.mherscode.minibank.model;

import com.mherscode.minibank.model.enums.TransType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TRANS_DATE")
    private LocalDateTime transDate;

    @Column(name = "TRANS_TYPE")
    private TransType type;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TRANS_BALANCE")
    private BigDecimal availableBalance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    public Transaction() {
    }

    public Transaction(LocalDateTime transDate, TransType type, double amount, String description, BigDecimal availableBalance, Account account) {
        this.transDate = transDate;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.availableBalance = availableBalance;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTransDate() {
        return transDate;
    }

    public void setTransDate(LocalDateTime transDate) {
        this.transDate = transDate;
    }

    public TransType getType() {
        return type;
    }

    public void setType(TransType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

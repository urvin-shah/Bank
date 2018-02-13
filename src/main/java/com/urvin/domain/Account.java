package com.urvin.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @OneToOne
    private User user;
    private Currancy currancy;
    private Double balance;

    public Account(){}

    public Account(Long accountId, User user, Currancy currancy, Double balance) {
        this.accountId = accountId;
        this.user = user;
        this.currancy = currancy;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Currancy getCurrancy() {
        return currancy;
    }

    public void setCurrancy(Currancy currancy) {
        this.currancy = currancy;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

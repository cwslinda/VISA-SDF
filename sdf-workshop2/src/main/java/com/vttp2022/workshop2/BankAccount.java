package com.vttp2022.workshop2;


import java.util.List;
import java.util.LinkedList;
// package to generate UUID
import java.util.UUID;
import java.time.LocalDateTime;



public class BankAccount {
    // bank account
    private String name = "";
    private String accID = UUID.randomUUID().toString().substring(0,8);
    private float balance = 0;
    private List<String> transaction = new  LinkedList<>();

    private boolean isClosed = false;

    private LocalDateTime accountCreationDate;
    private LocalDateTime accountClosingDate;



    // bank account name - constructor
    public BankAccount(String name){
        this.name = name;
        this.balance = 0;
    }

    // bank account name with initial balance - 2nd constructor
    public BankAccount(String name, float initialBal){
        this.name = name;
        this.balance = initialBal;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public String getAccID() {
        return accID;
    }


    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<String> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<String> transaction) {
        this.transaction = transaction;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public LocalDateTime getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDateTime accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public LocalDateTime getAccountClosingDate() {
        return accountClosingDate;
    }

    public void setAccountClosingDate(LocalDateTime  accountClosingDate) {
        this.accountClosingDate = accountClosingDate;
    }

    public float withdraw(String withdrawAmt){
        Float withdrawAmtF = null;

        try {
            withdrawAmtF = Float.parseFloat(withdrawAmt);
            if (withdrawAmtF.floatValue() <= 0){
                throw new IllegalArgumentException("Withdrawal amount cannot be negative or zero");
            }

            if(this.isClosed()){
                throw new IllegalArgumentException("Account is closed!");
            }

            if (withdrawAmtF.floatValue() > this.balance){
                throw new IllegalArgumentException("Account do not have this much money");
            }

            // update the class variable of the deposit amount
            this.balance = this.balance - withdrawAmtF.floatValue();
            // construct the transaction history event log
            StringBuilder txnStrbld = new StringBuilder();
            txnStrbld.append("Withdraw $");
            txnStrbld.append(withdrawAmtF.floatValue());
            txnStrbld.append(" at ");
            txnStrbld.append(LocalDateTime.now());
            System.out.println(txnStrbld.toString());
            // save the event log into the linkedlist
            transaction.add(txnStrbld.toString());

        } catch(NumberFormatException e){
            System.err.print(e);
            throw new IllegalArgumentException("Invalid withdraw amount");

        }

        return this.balance;

    }

    public void deposit(String depositAmt){
        try {
            Float depositAmtF = Float.parseFloat(depositAmt);
            if (depositAmtF.floatValue() <= 0){
                throw new IllegalArgumentException("Deposit amount cannot be negative or zero");
            }

            if(this.isClosed()){
                throw new IllegalArgumentException("Account is closed!");
            }

            // update the class variable of the deposit amount
            this.balance = this.balance + depositAmtF.floatValue();
            // construct the transaction history event log
            StringBuilder txnStrbld = new StringBuilder();
            txnStrbld.append("Deposit $");
            txnStrbld.append(depositAmtF.floatValue());
            txnStrbld.append(" at ");
            txnStrbld.append(LocalDateTime.now());
            System.out.println(txnStrbld.toString());
            // save the event log into the linkedlist
            transaction.add(txnStrbld.toString());

        } catch(NumberFormatException e){
            System.err.print(e);
            throw new IllegalArgumentException("Invalid deposit amount");

        }
    }
}


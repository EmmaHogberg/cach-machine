package com.emma;

public class BankCustomer {

    private int id;
    private int pinCode;
    private int pinAttempts;
    private boolean isCardBocked;
    private int accountBalance;
    String bankName;


    public BankCustomer(int id, int pinCode, int pinAttempts, boolean isCardBocked, int account, String bankName) {
        this.id = id;
        this.pinCode = pinCode;
        this.pinAttempts = pinAttempts;
        this.isCardBocked = isCardBocked;
        this.accountBalance = account;
        this.bankName = bankName;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public int getPinAttempts() {
        return pinAttempts;
    }

    public void setPinAttempts(int pinAttempts) {
        this.pinAttempts = pinAttempts;
    }

    public boolean isCardBocked() {
        return isCardBocked;
    }

    public void setCardBocked(boolean cardBocked) {
        isCardBocked = cardBocked;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public boolean isPinCodeCorrect(int pinCodeInput) {
        return pinCodeInput == pinCode;
    }


}

package com.emma;

public class BankingService {

    private final BankRepository bankRepository;
    private static final String bankName = "BankOfSweden";

    public BankingService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }


    // Static method to get bank name
    public static String getNameOfBank() {
        return bankName;
    }


    // Method to login customer
    public BankCustomer login(int id, int pinCodeInput) {
        BankCustomer bankCustomer = bankRepository.getCustomerByID(id);

        if (bankCustomer == null) {
            return null;
        }
        if (bankCustomer.isCardBocked()) {
            return null;
        }

        if (!bankCustomer.isPinCodeCorrect(pinCodeInput)) {
            bankCustomer.setPinAttempts(bankCustomer.getPinAttempts()+1);
            if (bankCustomer.getPinAttempts() == 3) {
                bankCustomer.setCardBocked(true);
            }
            return null;
        }
        bankCustomer.setPinAttempts(0);

        return bankCustomer;
    }


    // Method for message to user when wrong pin input
    public String sendMessageToCustomerWhenWrongPinInput(BankCustomer activeCustomer) {
        String message = "";

        switch (activeCustomer.getPinAttempts()) {
            case 1 -> {
                message = "Wrong pin input, you have 2 more attempts";
            }
            case 2 -> {
                message = "Wrong pin input, you have 1 more attempt";
            }
            case 3 -> {
                message = "You hade given the wrong pincode input three times, the card is blocked";
            }
        }
        System.out.println(message);
        return message;
    }


    // Method for balance
    public int getAccountBalance(BankCustomer activeCustomer) {
        return activeCustomer.getAccountBalance();
    }


    // Methods for deposit
    public boolean RequestDepositToAccount(BankCustomer activeCustomer, int deposit) {
        if (deposit == 0) {
            return false;
        }
        addDepositToAccount(activeCustomer,deposit);
        return true;
    }

    public void addDepositToAccount(BankCustomer activeCustomer, int deposit) {
        activeCustomer.setAccountBalance(activeCustomer.getAccountBalance() + deposit);
    }


    // Methods for withdraw
    public int requestWithdraw(BankCustomer activeCustomer, int moneyRequest) {

        int balance = activeCustomer.getAccountBalance();
        if (balance >= moneyRequest) {
            withdrawMoney(activeCustomer, moneyRequest);
            return moneyRequest;
        }

        return 0;
    }

    public void withdrawMoney(BankCustomer activeCustomer, int moneyRequest) {

        int balance = activeCustomer.getAccountBalance();
        activeCustomer.setAccountBalance(balance - moneyRequest);
    }
}

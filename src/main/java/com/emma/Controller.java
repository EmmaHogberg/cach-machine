package com.emma;

public class Controller {

    BankingService bankingService;
    BankCustomer activeCustomer;

    public Controller(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    public void useATM() {
        int cardID = 123;
        int pinCodeInput = 8888;
        double accountBalance;

        boolean depositAction;
        int amountToDeposit = 300;

        int requestedMoney = 400;
        int payout;

        String messageToCustomer;




        activeCustomer = bankingService.login(cardID, pinCodeInput);
        messageToCustomer = bankingService.sendMessageToCustomerWhenWrongPinInput(activeCustomer);


        if (activeCustomer != null) {

            // Balance
            accountBalance = bankingService.getAccountBalance(activeCustomer);
            messageToCustomer = "Balance: " + activeCustomer.getAccountBalance();


            // Deposit
            depositAction = bankingService.RequestDepositToAccount(activeCustomer, amountToDeposit);
            if (depositAction) {
                System.out.println(amountToDeposit + " :- is added to your account"); // Just for fun
                System.out.println("Updated balance: " + activeCustomer.getAccountBalance()); // Just for fun
            } else {
                System.out.println("The deposit failed, please try again"); // Just for fun
            }


            // Withdraw
            payout = bankingService.requestWithdraw(activeCustomer, requestedMoney);
            if (requestedMoney == payout) {
                messageToCustomer = requestedMoney + " is now paid out";
            }
            if (payout == 0) {
                messageToCustomer = "Withdraw failed, There is not enough money in the account";
            }


        }




    }


}

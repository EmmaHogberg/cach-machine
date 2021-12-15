package com.emma;

public class BankingService {

    private final CustomersService customersService;
    private BankCustomer activeCustomer;

    public BankingService(CustomersService customersService) {
        this.customersService = customersService;
    }


    public boolean login(int id, int pinCodeInput) {
        BankCustomer customer = customersService.getCustomerByID(id);

        if (customer == null) {
            return false;
        }
        if (!customersService.isCardOpenForAnotherPinCodeTry(activeCustomer)) {
            return false;
        }

        if (!customer.isPinCodeCorrect(pinCodeInput)) {
            customer.setPinAttempts(customer.getPinAttempts()+1);
            return false;
        }
        customer.setPinAttempts(0);
        activeCustomer = customer;
        return true;
    }




    public int getAccountBalance() {
        System.out.println("Balance: " + activeCustomer.getAccountBalance());
        return activeCustomer.getAccountBalance();
    }

    public int depositAndReturnNewAccountBalance(int deposit) {
        activeCustomer.setAccountBalance(activeCustomer.getAccountBalance() + deposit);

        System.out.println("Updated balance: " + activeCustomer.getAccountBalance());
        return activeCustomer.getAccountBalance();
    }


//    public boolean isCardOpenForAnotherPinCodeTry() {
//        switch (activeCustomer.getPinAttempts()) {
//            case 1 -> System.out.println("Wrong pin input, you have 2 more attempts");
//            case 2 -> System.out.println("Wrong pin input, you have 1 more attempts");
//            case 3 -> {
//                activeCustomer.setCardBocked(true);
//                System.out.println("You hade given the wrong pincode input three times, the card is blocked");
//                return false;
//            }
//        }
//        return true;
//    }









//    private BankCustomer findCustomerByID(int id) {
//
//        ArrayList<BankCustomer> listOfCustomers = customersService.getCostumers();
//
//        for (BankCustomer customer : listOfCustomers) {
//            if (customer.getId() == id) {
//                return customer;
//            }
//        }
//        return null;
//    }

}

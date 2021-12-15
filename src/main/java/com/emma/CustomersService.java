package com.emma;

import java.util.ArrayList;

public class CustomersService {

    ArrayList<BankCustomer> listOfCustomers = new ArrayList<>();

    public CustomersService() {

    }

    public BankCustomer getCustomerByID(int id) {

        ArrayList<BankCustomer> listOfCustomers = getCustomers();

        for (BankCustomer customer : listOfCustomers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public boolean isCardOpenForAnotherPinCodeTry(BankCustomer activeCustomer) {
        switch (activeCustomer.getPinAttempts()) {
            case 1 -> System.out.println("Wrong pin input, you have 2 more attempts");
            case 2 -> System.out.println("Wrong pin input, you have 1 more attempts");
            case 3 -> {
                activeCustomer.setCardBocked(true);
                System.out.println("You hade given the wrong pincode input three times, the card is blocked");
                return false;
            }
        }
        return true;
    }


    private ArrayList<BankCustomer> getCustomers() {

        return listOfCustomers;
    }

}

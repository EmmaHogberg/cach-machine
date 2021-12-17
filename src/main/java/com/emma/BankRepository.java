package com.emma;

import java.util.ArrayList;

public class BankRepository {

    ArrayList<BankCustomer> listOfCustomers;

    public BankRepository(ArrayList<BankCustomer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;

    }

    public BankCustomer getCustomerByID(int id) {

        for (BankCustomer customer : listOfCustomers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

}

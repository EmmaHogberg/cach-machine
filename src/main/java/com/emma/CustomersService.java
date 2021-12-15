package com.emma;

import java.util.ArrayList;

public class CustomersService {

    ArrayList<BankCustomer> listOfCustomers;

    public CustomersService(ArrayList<BankCustomer> listOfCustomers) {
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

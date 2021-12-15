package com.emma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomersServiceTest {

    private CustomersService customersService;
    private CustomerDataBase customerDataBaseMock;
    ArrayList<BankCustomer> listOfCustomers;
    private BankCustomer customerOne = new BankCustomer(123, 8888, 0, false, 2750, "BankOfSweden");
    private BankCustomer customerTwo = new BankCustomer(234, 4545, 1, false, 26300, "BankOfSweden");
    private BankCustomer customerThree = new BankCustomer(345, 9894, 2, false, 27, "BankOfSweden");
    private BankCustomer customerFour = new BankCustomer(456, 7878, 3, true, 554, "BankOfSweden");


    @BeforeEach
    void setup() {
         customerDataBaseMock = mock(CustomerDataBase.class);
         listOfCustomers = new ArrayList<>();
         listOfCustomers.add(customerOne);
         listOfCustomers.add(customerTwo);
         listOfCustomers.add(customerThree);
         listOfCustomers.add(customerFour);
         customersService = new CustomersService(listOfCustomers);
    }



    // getCustomerByID()
    @Test
    void should_ReturnCorrectCustomer_when_MatchingID() {
        when(customerDataBaseMock.getCustomers()).thenReturn(listOfCustomers);

        BankCustomer expected = customerOne;
        BankCustomer actual = customersService.getCustomerByID(123);

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnNull_when_NoMatchingID() {
        when(customerDataBaseMock.getCustomers()).thenReturn(listOfCustomers);

        BankCustomer actual = customersService.getCustomerByID(888);

        assertNull(actual);
    }

}
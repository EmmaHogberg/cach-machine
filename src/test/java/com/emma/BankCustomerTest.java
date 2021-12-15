package com.emma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

class BankCustomerTest {

    private BankCustomer bankCustomerMock;
    private BankCustomer customerOne = new BankCustomer(123, 8888, 0, false, 2750, "BankOfSweden");
    private BankCustomer customerTwo = new BankCustomer(234, 4545, 1, false, 26300, "BankOfSweden");
    private BankCustomer customerThree = new BankCustomer(345, 9894, 2, false, 27, "BankOfSweden");
    private BankCustomer customerFour = new BankCustomer(456, 7878, 3, true, 554, "BankOfSweden");


    @Test
    void should_ReturnTrue_when_ZeroWrongPinInput() {
        int pinAttempts = customerOne.getPinAttempts();


    }
}
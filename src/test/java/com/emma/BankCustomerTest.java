package com.emma;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankCustomerTest {

    private BankCustomer customerOne = new BankCustomer(123, 8888, 0, false, 2750, "BankOfSweden");

    @Test
    void should_ReturnTrue_when_CorrectPinInput() {
        assertTrue(customerOne.isPinCodeCorrect(8888));
    }

    @Test
    void should_ReturnFalse_when_NoCorrectPinInput() {
        assertFalse(customerOne.isPinCodeCorrect(9898));
    }
}
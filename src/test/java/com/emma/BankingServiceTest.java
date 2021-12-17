package com.emma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BankingServiceTest {

    private BankRepository bankRepositoryMock;
    private BankingService bankingService;
    private BankCustomer customerOne = new BankCustomer(123, 8888, 0, false, 2750, "BankOfSweden");
    private BankCustomer customerTwo = new BankCustomer(234, 4545, 1, false, 26300, "BankOfSweden");
    private BankCustomer customerThree = new BankCustomer(345, 9894, 2, false, 27, "BankOfSweden");
    private BankCustomer customerFour = new BankCustomer(456, 7878, 3, true, 554, "BankOfSweden");


    @BeforeEach
    void setup() {
        bankRepositoryMock = mock(BankRepository.class);
        bankingService = new BankingService(bankRepositoryMock);
    }


    // login()
    @Test
    void should_ReturnNull_when_NoMatchingId() {
        when(bankRepositoryMock.getCustomerByID(777)).thenReturn(customerOne);
        BankCustomer actual = bankingService.login(777, 4545);
        assertNull(actual);
    }

    @Test
    void should_ReturnNull_when_CardIsBlockedEvenIfIdAndPinCorrect() {
        when(bankRepositoryMock.getCustomerByID(456)).thenReturn(customerFour);
        BankCustomer actual = bankingService.login(456, 7878);
        assertNull(actual);
    }

    @Test
    void should_ReturnCustomer_when_IdAndCorrectPinCodeInput() {
        when(bankRepositoryMock.getCustomerByID(123)).thenReturn(customerOne);
        BankCustomer expected = customerOne;
        BankCustomer actual = bankingService.login(123, 8888);
        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnNull_when_IdAndNoCorrectPinCodeInput() {
        when(bankRepositoryMock.getCustomerByID(123)).thenReturn(customerOne);
        BankCustomer actual = bankingService.login(123, 4545);
        assertNull(actual);
    }

    @Test
    void should_ReturnNull_and_SetCardAsBlocked_when_NoCorrectPinCodeInputThirdTime() {
        when(bankRepositoryMock.getCustomerByID(345)).thenReturn(customerThree);

        assertNull(bankingService.login(345, 3467));

        boolean isCardSetAsBlocked = customerThree.isCardBocked();
        assertTrue(isCardSetAsBlocked);
    }

    @Test
    void should_SetPinAttemptsToZero_when_CorrectPinCode() {
        when(bankRepositoryMock.getCustomerByID(345)).thenReturn(customerThree);

        int firstExpected = 2;
        int firstActual = customerThree.getPinAttempts();

        bankingService.login(345, 9894);
        int expected = 0;
        int actual = customerThree.getPinAttempts();

        assertEquals(firstExpected, firstActual);
        assertEquals(expected, actual);
    }



    // sendMessageToCustomerWhenWrongPinInput()
    @Test
    void should_ReturnTrue_when_ZeroWrongPinInput() {
        String expected = "";
        String actual = bankingService.sendMessageToCustomerWhenWrongPinInput(customerOne);
        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnCorrectMassage_when_OneWrongPinInput() {
        String expected = "Wrong pin input, you have 2 more attempts";
        String actual = bankingService.sendMessageToCustomerWhenWrongPinInput(customerTwo);
        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnCorrectMessage_when_TwoWrongPinInput() {
        String expected = "Wrong pin input, you have 1 more attempt";
        String actual = bankingService.sendMessageToCustomerWhenWrongPinInput(customerThree);
        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnCorrectMessage_when_ThreeWrongPinInput() {
        String expected = "You hade given the wrong pincode input three times, the card is blocked";
        String actual = bankingService.sendMessageToCustomerWhenWrongPinInput(customerFour);
        assertEquals(expected, actual);
    }


    // getAccountBalance()
    @Test
    void should_ReturnAccountBalance_when_ActionGetAccountBalance() {
        int expected = customerOne.getAccountBalance();
        int actual = bankingService.getAccountBalance(customerOne);

        assertEquals(expected, actual);
    }


    // addDepositToAccount()
    @Test
    void should_UpdateAccountBalance_when_DepositActionTrue() {
        int expected = customerOne.getAccountBalance() + 4000;

        bankingService.RequestDepositToAccount(customerOne, 4000);
        int actual = customerOne.getAccountBalance();

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnTrue_when_DepositActionPerformed() {
        assertTrue(bankingService.RequestDepositToAccount(customerOne, 500));
    }

    @Test
    void should_ReturnFalse_when_DepositAction_and_NoDepositValue() {
        assertFalse(bankingService.RequestDepositToAccount(customerOne, 0));
    }


    // withdrawMoney()
    @Test
    void should_UpdateAccountBalance_when_WithdrawMoneyActionPerformed() {
        int expected = customerOne.getAccountBalance() - 300;

        bankingService.withdrawMoney(customerOne, 300);
        int actual = customerOne.getAccountBalance();

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnRequestedMoney_when_EnoughMoneyInAccountBalance() {
        int expected = 400;
        int actual = bankingService.requestWithdraw(customerOne, 400);

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnZeroMoney_when_NotEnoughMoneyInAccountBalance() {
        int expected = 0;
        int actual = bankingService.requestWithdraw(customerOne, 5000);

        assertEquals(expected, actual);
    }

}
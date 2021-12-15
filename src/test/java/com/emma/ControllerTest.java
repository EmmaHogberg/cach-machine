package com.emma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


class ControllerTest {

    private BankingService bankingServiceMock;
    private Controller controller;
    private BankCustomer customerOne = new BankCustomer(123, 8888, 0, false, 2750, "BankOfSweden");


    @BeforeEach
    void setup() {
        bankingServiceMock = mock(BankingService.class);
        controller = new Controller(bankingServiceMock);
    }



    @Test
    void should_RunDepositAction_when_DepositRequest() {
        bankingServiceMock.addDepositToAccount(customerOne, 4000);

        verify(bankingServiceMock ,times(1)).addDepositToAccount(customerOne, 4000);
        verifyNoMoreInteractions(bankingServiceMock);
    }

    @Test
    void should_RunWithdrawMoneyAction_when_WithdrawMoneyRequest() {
        bankingServiceMock.withdrawMoney(customerOne, 500);

        verify(bankingServiceMock ,times(1)).withdrawMoney(customerOne, 500);
        verifyNoMoreInteractions(bankingServiceMock);
    }

}
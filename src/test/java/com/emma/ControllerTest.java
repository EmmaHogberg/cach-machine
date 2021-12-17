package com.emma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


class ControllerTest {

    private BankingService bankingServiceMock;
    private final BankCustomer customer = new BankCustomer(123, 8888, 0, false, 2750, "BankOfSweden");


    @BeforeEach
    void setup() {
        bankingServiceMock = mock(BankingService.class);
    }


    // Verify
    @Test
    void should_RunDepositAction_when_DepositRequest() {
        bankingServiceMock.addDepositToAccount(customer, 4000);

        verify(bankingServiceMock ,times(1)).addDepositToAccount(customer, 4000);
        verifyNoMoreInteractions(bankingServiceMock);
    }

    @Test
    void should_RunWithdrawMoneyAction_when_WithdrawMoneyRequest() {
        bankingServiceMock.withdrawMoney(customer, 500);

        verify(bankingServiceMock ,times(1)).withdrawMoney(customer, 500);
        verifyNoMoreInteractions(bankingServiceMock);

    }


    // Mock static
    @Test
    void should_ReturnBankName() {
        try (MockedStatic<BankingService> mockedStatic = mockStatic(BankingService.class)) {
            mockedStatic.when(() -> BankingService.getNameOfBank()).thenReturn("BankOfSweden");
            String actual = BankingService.getNameOfBank();

            assertEquals("BankOfSweden", actual);
        }
    }

}
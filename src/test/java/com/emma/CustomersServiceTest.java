package com.emma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomersServiceTest {

    private CustomersService customersServiceMock;
    private BankingService bankingService;
    private ArrayList<BankCustomer> listOfCustomers;
    private BankCustomer customerOne = new BankCustomer(123, 8888, 0, false, 2750, "BankOfSweden");
    private BankCustomer customerTwo = new BankCustomer(234, 4545, 1, false, 26300, "BankOfSweden");
    private BankCustomer customerThree = new BankCustomer(345, 9894, 2, false, 27, "BankOfSweden");
    private BankCustomer customerFour = new BankCustomer(456, 7878, 3, true, 554, "BankOfSweden");


    @BeforeEach
    void setup() {

        customersServiceMock = mock(CustomersService.class);
        bankingService = new BankingService(customersServiceMock);
        listOfCustomers = new ArrayList<>();
        listOfCustomers.add(customerOne);
        listOfCustomers.add(customerTwo);
        listOfCustomers.add(customerThree);
        listOfCustomers.add(customerFour);
    }

    @Test
    void should_ReturnCorrectCustomer_when_GetCustomerByID() {
        when(customersServiceMock.getCustomerByID(123)).thenReturn(customerOne);

        BankCustomer expected = customerOne;
        BankCustomer actual = customersServiceMock.getCustomerByID(123);

        assertEquals(expected, actual);
    }











    @Test
    void should_ReturnFalse_when_ActiveCostumerAndNoCorrectPinCode() {
        when(customersServiceMock.getCustomerByID(0)).thenReturn(customerOne);
        assertFalse(bankingService.login(customerOne.getId(), 4545));
    }

    @Test
    void should_ReturnFalse_when_ActiveCostumerDoesNotExistInListOfCostumers() {
        when(customersServiceMock.getCustomerByID(777)).thenReturn(null);
        assertFalse(bankingService.login(777, 4545));
    }












//    @Test
//    void should_ReturnTrue_when_CustomerWithZeroWrongPinInput() {
//        when(customersServiceMock.getCostumers()).thenReturn(listOfCustomers);
//        assertTrue(bankingService.login(customerOne.getId(), customerOne.getPinCode()));
//    }
//
//    @Test
//    void should_ReturnTrue_when_CustomerWithOneWrongPinInput() {
//        when(customersServiceMock.getCostumers()).thenReturn(listOfCustomers);
//        assertTrue(bankingService.login(customerTwo.getId(), customerTwo.getPinCode()));
//    }
//
//    @Test
//    void should_ReturnTrue_when_CustomerWithTwoWrongPinInput() {
//        when(customersServiceMock.getCostumers()).thenReturn(listOfCustomers);
//        assertTrue(bankingService.login(customerThree.getId(), customerThree.getPinCode()));
//    }
//
//    @Test
//    void should_ReturnTrue_when_CustomerWithThreeWrongPinInput() {
//        when(customersServiceMock.getCostumers()).thenReturn(listOfCustomers);
//        assertFalse(bankingService.login(customerFour.getId(), customerFour.getPinCode()));
//    }
//
//    @Test
//    void should_ReturnAccountBalance_when_ActionGetAccountBalance() {
//        when(customersServiceMock.getCostumers()).thenReturn(listOfCustomers);
//        bankingService.login(customerOne.getId(), customerOne.getPinCode());
//
//        int expected = customerOne.getAccountBalance();
//        int actual = bankingService.getAccountBalance();
//
//        assertEquals(expected, actual);
//    }

}
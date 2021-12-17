package com.emma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BankRepositoryTest {

    private BankRepository bankRepository;
    private BankDatabase customerDataBaseMock;
    ArrayList<BankCustomer> listOfCustomers;
    private final BankCustomer customerOne = new BankCustomer(123, 8888, 0, false, 2750, "BankOfSweden");
    private final BankCustomer customerTwo = new BankCustomer(234, 4545, 1, false, 26300, "BankOfSweden");
    private final BankCustomer customerThree = new BankCustomer(345, 9894, 2, false, 27, "BankOfSweden");
    private final BankCustomer customerFour = new BankCustomer(456, 7878, 3, true, 554, "BankOfSweden");


    @BeforeEach
    void setup() {
         customerDataBaseMock = mock(BankDatabase.class);
         listOfCustomers = new ArrayList<>();
         listOfCustomers.add(customerOne);
         listOfCustomers.add(customerTwo);
         listOfCustomers.add(customerThree);
         listOfCustomers.add(customerFour);
         bankRepository = new BankRepository(listOfCustomers);
    }



    // getCustomerByID()
    @Test
    void should_ReturnCorrectCustomer_when_MatchingID() {
        when(customerDataBaseMock.getCustomers()).thenReturn(listOfCustomers);

        BankCustomer actual = bankRepository.getCustomerByID(123);

        assertEquals(customerOne, actual);
    }

    @Test
    void should_ReturnNull_when_NoMatchingID() {
        when(customerDataBaseMock.getCustomers()).thenReturn(listOfCustomers);

        BankCustomer actual = bankRepository.getCustomerByID(888);

        assertNull(actual);
    }

}
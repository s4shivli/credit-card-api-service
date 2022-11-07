package com.dove.card.repository;

import com.dove.card.entity.CreditCardDBEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.dove.card.data.TestData.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardServiceRepositoryTest {

    @Mock
    private DataBaseAccessJpa dataBaseAccessJpa;

    @InjectMocks
    CreditCardRepository creditCardRepository;
    List<CreditCardDBEntity> listOfCustomer = List.of(AMITAVA, SHIVLI, PRIYANKA, RIMPA);
    public static final CreditCardDBEntity RIMPA = new CreditCardDBEntity(100500, "Rimpa", "Saha", getDate("07-2027"), 54237.45, "Bronze");


    @Test
    void test_add_Customers_Credit_Card() {

        when(dataBaseAccessJpa.saveAll(listOfCustomer)).thenReturn(listOfCustomer);
        boolean result = creditCardRepository.addCards(listOfCustomer);
        verify(dataBaseAccessJpa, times(1)).saveAll(listOfCustomer);
        Assertions.assertTrue(result);
    }

    @Test
    void test_to_get_All_Customers_CreditCard() {
        when(dataBaseAccessJpa.findAll()).thenReturn(listOfCustomer);
        Set<CreditCardDBEntity> getAllCustomer = creditCardRepository.getAllCards();
        int size = getAllCustomer.size();
        Assertions.assertEquals(4, size);
        verify(dataBaseAccessJpa, times(1)).findAll();
    }

    @Test
    void test_search_Customer_By_CardNumber() {
        when(dataBaseAccessJpa.findById(100200)).thenReturn(Optional.of(AMITAVA));
        Optional<CreditCardDBEntity> matchedCustomer = creditCardRepository.getCard(100200);
        verify(dataBaseAccessJpa, times(1)).findById(100200);
        Assertions.assertSame(AMITAVA, matchedCustomer.get());
    }

    @Test
    void test_update_CustomerCreditCard_If_It_Is_Already_in_database() {
        when(dataBaseAccessJpa.findById(RIMPA.getCardNumber())).thenReturn(Optional.of(RIMPA));
        when(dataBaseAccessJpa.save(RIMPA)).thenReturn(RIMPA);
        String updated = creditCardRepository.updateCard(RIMPA);
        verify(dataBaseAccessJpa, times(1)).save(RIMPA);
        verify(dataBaseAccessJpa, times(1)).findById(RIMPA.getCardNumber());

        Assertions.assertEquals("Updated", updated);

    }

    @Test
    void test_Create_CustomerCreditCard_If_It_Is_Not_In_database() {

        when(dataBaseAccessJpa.findById(KATE.getCardNumber())).thenReturn(Optional.empty());

        when(dataBaseAccessJpa.save(KATE)).thenReturn(KATE);

        String created = creditCardRepository.updateCard(KATE);

        verify(dataBaseAccessJpa, times(1)).findById(KATE.getCardNumber());

        verify(dataBaseAccessJpa, times(1)).save(KATE);

        Assertions.assertEquals("New customer credit card has been created", created);
    }

    @Test
    void test_delete_Customer_CreditCardDetails_By_CardNumber() {
        dataBaseAccessJpa.deleteById(100300);
        verify(dataBaseAccessJpa, times(1)).deleteById(100300);

    }
}
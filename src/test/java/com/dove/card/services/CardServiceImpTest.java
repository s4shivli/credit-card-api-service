package com.dove.card.services;

import com.dove.card.model.CreditCardVO;
import com.dove.card.repository.CreditCardRepo;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardServiceImpTest {
    @InjectMocks
    private CardServiceImp cardServiceImp;

    @Mock
    private CreditCardRepo creditCardRepo;

    @Test
    void testCardsAddedSuccessfully() {
        List<CreditCardVO> creditCardList = List.of(CardForAmitava);
        when(creditCardRepo.addCards(anyList())).thenReturn(true);
        boolean isCardAdded = cardServiceImp.addCards(creditCardList);
        Assertions.assertTrue(isCardAdded);
    }

    @Test
    void testCardsFailedToAdd() {
        List<CreditCardVO> creditCardList = List.of(CardForAmitava);
        when(creditCardRepo.addCards(anyList())).thenReturn(false);
        boolean isCardAdded = cardServiceImp.addCards(creditCardList);
        Assertions.assertFalse(isCardAdded);
    }

    @Test
    void testCardsAddThrowsException() {
        List<CreditCardVO> creditCardList = List.of(CardForAmitava);
        when(creditCardRepo.addCards(anyList())).thenThrow(new RuntimeException("Error in DB"));
        Assertions.assertThrows(RuntimeException.class, () -> cardServiceImp.addCards(creditCardList));
    }


    @Test
    void getAllCards() {
        when(creditCardRepo.getAllCards()).thenReturn(Set.of(AMITAVA, RIMPA, PRIYANKA));
        Set<CreditCardVO> creditCardVOSet = cardServiceImp.getAllCards();
        Assertions.assertEquals(3, creditCardVOSet.size());
    }

    @Test
    void testWhenCardDetailsIsNotPresent() {
        when(creditCardRepo.getCard(100)).thenReturn(Optional.empty());
        Optional<CreditCardVO> creditCardVOSet = cardServiceImp.getCard(100);
        Assertions.assertTrue(creditCardVOSet.isEmpty());
    }

    @Test
    void testWhenCardDetailsIsPresent() {
        when(creditCardRepo.getCard(100)).thenReturn(Optional.of(AMITAVA));
        Optional<CreditCardVO> creditCardVOSet = cardServiceImp.getCard(100);
        Assertions.assertTrue(creditCardVOSet.isPresent());
        Assertions.assertEquals(AMITAVA.getFirstName(), creditCardVOSet.get().getFirstName());
    }


    @Test
    void deleteCard() {
        when(creditCardRepo.deleteCard(100)).thenReturn("Deleted");
        String deleteResponse = cardServiceImp.deleteCard(100);
        Assertions.assertEquals("Deleted", deleteResponse);
    }


}
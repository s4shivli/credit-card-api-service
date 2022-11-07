package com.dove.card.services;

import com.dove.card.model.CreditCardVO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CardService {

    boolean addCards(List<CreditCardVO> creditCardList);

    Optional<CreditCardVO> getCard(Integer cardNumber);

    Set<CreditCardVO> getAllCards();

    String updateCardDetails(CreditCardVO creditCardVO);

    String deleteCard(Integer cardNumber);

}

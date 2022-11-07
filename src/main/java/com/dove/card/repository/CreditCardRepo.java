package com.dove.card.repository;

import com.dove.card.entity.CreditCardDBEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CreditCardRepo {
    boolean addCards(List<CreditCardDBEntity> creditCardList);

    Set<CreditCardDBEntity> getAllCards();

    Optional<CreditCardDBEntity> getCard(Integer cardNumber);

    String updateCard(CreditCardDBEntity customersCreditCard);

    String deleteCard(Integer cardNumber);

}

package com.dove.card.repository;

import com.dove.card.entity.CreditCardDBEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@Slf4j
public class CreditCardRepository implements CreditCardRepo {


    public static final String DELETED = "Deleted";
    private final DataBaseAccessJpa dataBaseAccessJpa;

    CreditCardRepository(DataBaseAccessJpa dataBaseJpa) {
        this.dataBaseAccessJpa = dataBaseJpa;
    }

    @Override
    public boolean addCards(List<CreditCardDBEntity> creditCardList) {
        dataBaseAccessJpa.saveAll(creditCardList);
        return true;
    }


    @Override
    public Set<CreditCardDBEntity> getAllCards() {
        List<CreditCardDBEntity> getAllCreditCardRecord = dataBaseAccessJpa.findAll();
        return new HashSet<>(getAllCreditCardRecord);
    }

    @Override
    public Optional<CreditCardDBEntity> getCard(Integer cardNumber) {
        Optional<CreditCardDBEntity> searchedCreditCard = dataBaseAccessJpa.findById(cardNumber);

        if (searchedCreditCard.isPresent()) {
            return searchedCreditCard;
        }
        log.info("No credit Card has found with this card number");
        return Optional.empty();
    }

    @Override
    public String updateCard(CreditCardDBEntity creditCardToUpdate) {
        Optional<CreditCardDBEntity> customerIsPresent = dataBaseAccessJpa.findById(creditCardToUpdate.getCardNumber());
        if (customerIsPresent.isPresent()) {
            dataBaseAccessJpa.save(creditCardToUpdate);
            return "Updated";
        } else {
            dataBaseAccessJpa.save(creditCardToUpdate);
            return "New customer credit card has been created";

        }
    }

    @Override
    public String deleteCard(Integer cardNumber) {
        dataBaseAccessJpa.deleteById(cardNumber);
        return DELETED;
    }
}

package com.dove.card.services;

import com.dove.card.entity.CreditCardDBEntity;
import com.dove.card.model.CreditCardVO;
import com.dove.card.repository.CreditCardRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CardServiceImp implements CardService {

    private final CreditCardRepo creditCardRepo;

    public CardServiceImp(CreditCardRepo creditCardRepo) {
        this.creditCardRepo = creditCardRepo;
    }

    private CreditCardVO creditCardBackToFront(CreditCardDBEntity creditCardDBEntity) {
        return CreditCardVO.builder()
                .cardNumber(creditCardDBEntity.getCardNumber())
                .cardType(creditCardDBEntity.getCardType())
                .firstName(creditCardDBEntity.getFirstName())
                .lastName(creditCardDBEntity.getLastName())
                .balance(creditCardDBEntity.getBalance())
                .expiryDate(creditCardDBEntity.getExpiryDate())
                .build();
    }

    private CreditCardDBEntity convertVOToCardDBEntity(CreditCardVO creditCardVO) {
        return CreditCardDBEntity.builder()
                .cardNumber(creditCardVO.getCardNumber())
                .cardType(creditCardVO.getCardType())
                .firstName(creditCardVO.getFirstName())
                .lastName(creditCardVO.getLastName())
                .balance(creditCardVO.getBalance())
                .expiryDate(creditCardVO.getExpiryDate())
                .build();
    }

    @Override
    public boolean addCards(List<CreditCardVO> creditCardList) {
        log.info("Requested to add cards of size {}", creditCardList.size());
        List<CreditCardDBEntity> addCustomersToDataBase = creditCardList.stream()
                .map(this::convertVOToCardDBEntity)
                .collect(Collectors.toList());
        log.info("Card added Successfully");
        return creditCardRepo.addCards(addCustomersToDataBase);
    }


    @Override
    public Set<CreditCardVO> getAllCards() {
        Set<CreditCardDBEntity> getAllDetailsFromDataBase = creditCardRepo.getAllCards();
        return getAllDetailsFromDataBase.stream()
                .map(this::creditCardBackToFront)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<CreditCardVO> getCard(Integer cardNumber) {
        Optional<CreditCardDBEntity> customerDetails = creditCardRepo.getCard(cardNumber);
        if (customerDetails.isPresent()) {
            return Optional.of(creditCardBackToFront(customerDetails.get()));
        } else {
            return Optional.empty();
        }

    }

    @Override
    public String updateCardDetails(CreditCardVO creditCardVO) {
        CreditCardDBEntity customerUpdate = this.convertVOToCardDBEntity(creditCardVO);
        return creditCardRepo.updateCard(customerUpdate);

    }

    @Override
    public String deleteCard(Integer cardNumber) {
        log.info("Requested to delete card #{}", cardNumber);
        return creditCardRepo.deleteCard(cardNumber);

    }
}

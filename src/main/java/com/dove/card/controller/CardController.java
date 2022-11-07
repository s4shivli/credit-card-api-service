package com.dove.card.controller;

import com.dove.card.model.CreditCardVO;
import com.dove.card.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("credit-card")
public class CardController {

    private final CardService cardServiceService;

    public CardController(CardService cardServiceService) {
        this.cardServiceService = cardServiceService;
    }

    @GetMapping("/cards")
    public Set<CreditCardVO> addCard() {
        return cardServiceService.getAllCards();
    }

    @PostMapping("/cards")
    public boolean addCustomer(@RequestBody List<CreditCardVO> cards) {
        return cardServiceService.addCards(cards);
    }

    @DeleteMapping("/card/{cardNumber}")
    public String deleteCard(@PathVariable Integer cardNumber) {
        return cardServiceService.deleteCard(cardNumber);
    }

    @PutMapping("/card")
    public String updateCard(@RequestBody CreditCardVO creditCardVO) {
        return cardServiceService.updateCardDetails(creditCardVO);
    }

    @GetMapping("/card/{cardNumber}")
    public ResponseEntity<CreditCardVO> getCard(@PathVariable Integer cardNumber) {
        Optional<CreditCardVO> creditCardVO = cardServiceService.getCard(cardNumber);

        if (creditCardVO.isPresent()) {
            return new ResponseEntity<>(creditCardVO.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}

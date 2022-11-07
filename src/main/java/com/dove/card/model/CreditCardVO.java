package com.dove.card.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class CreditCardVO {
    private Integer cardNumber;
    private String firstName;
    private String lastName;
    private Date expiryDate;
    private String cardType;
    private Double balance;
}

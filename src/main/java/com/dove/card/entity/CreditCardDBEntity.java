package com.dove.card.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Builder

public class CreditCardDBEntity {

    @Id
    private Integer cardNumber;
    private String firstName;
    private String lastName;
    private Date expiryDate;
    private Double balance;
    private String cardType;

    public CreditCardDBEntity() {

    }
}

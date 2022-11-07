package com.dove.card.repository;

import com.dove.card.entity.CreditCardDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataBaseAccessJpa extends JpaRepository<CreditCardDBEntity,Integer> {
}

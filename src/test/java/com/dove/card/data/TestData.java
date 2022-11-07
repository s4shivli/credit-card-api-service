package com.dove.card.data;

import com.dove.card.entity.CreditCardDBEntity;
import com.dove.card.model.CreditCardVO;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TestData {
    static SimpleDateFormat dateformat = new SimpleDateFormat("MM-yyyy");


    public static final CreditCardDBEntity AMITAVA = new CreditCardDBEntity(100200, "Amitava", "Biswas", getDate("01-2025"), 4537.45, "Gold");
    public static final CreditCardDBEntity SHIVLI = new CreditCardDBEntity(100300, "Shivli", "Mistry", getDate("05-2026"), 14537.45, "Silver");
    public static final CreditCardDBEntity PRIYANKA = new CreditCardDBEntity(100400, "Priyanka", "Singh", getDate("11-2025"), 54537.45, "Platinum");
    public static final CreditCardDBEntity RIMPA = new CreditCardDBEntity(100500, "Rimpa", "Saha", getDate("05-2026"), 24537.45, "Bronze");
    public static final CreditCardDBEntity KATE = new CreditCardDBEntity(100600, "Kate", "Holl", getDate("06-2029"), 64537.45, "Gold");

    public static final CreditCardVO CardForAmitava = CreditCardVO.builder().cardNumber(1).cardType("Credit").balance(100.45).build();


    @SneakyThrows
    public static Date getDate(String strDate) {
        return dateformat.parse(strDate);
    }
}

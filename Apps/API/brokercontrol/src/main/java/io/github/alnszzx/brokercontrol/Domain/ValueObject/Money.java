package io.github.alnszzx.brokercontrol.Domain.ValueObject;

import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@Value
public class Money {

    BigDecimal amount;
    Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        if (amount == null) {
            throw new IllegalArgumentException("Valor monetário não pode ser nulo");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Moeda não pode ser nula");
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
        this.currency = currency;
    }

    public static Money real(BigDecimal amount) {
        return new Money(amount, Currency.getInstance("BRL"));
    }

    public static Money zero() {
        return new Money(BigDecimal.ZERO, Currency.getInstance("BRL"));
    }
}

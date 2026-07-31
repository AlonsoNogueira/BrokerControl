package io.github.alnszzx.brokercontrol.Domain.ValueObject;

import lombok.Value;

@Value
public class PhoneNumber {

    String value;

    public PhoneNumber(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio");
        }
        String digits = value.replaceAll("\\D", "");
        if (digits.length() < 8 || digits.length() > 15) {
            throw new IllegalArgumentException("Telefone inválido: " + value);
        }
        this.value = digits;
    }
}

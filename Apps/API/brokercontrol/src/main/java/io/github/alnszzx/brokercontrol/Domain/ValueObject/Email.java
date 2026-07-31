package io.github.alnszzx.brokercontrol.Domain.ValueObject;

import lombok.Value;

import java.util.regex.Pattern;

@Value
public class Email {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    String value;

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("E-mail não pode ser nulo ou vazio");
        }
        String normalized = value.trim().toLowerCase();
        if (!EMAIL_PATTERN.matcher(normalized).matches()) {
            throw new IllegalArgumentException("E-mail inválido: " + value);
        }
        this.value = normalized;
    }
}

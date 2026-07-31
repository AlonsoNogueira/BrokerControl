package io.github.alnszzx.brokercontrol.Domain.ValueObject;

import lombok.Value;

@Value
public class Address {

    String street;
    String number;
    String complement;
    String neighborhood;
    String city;
    String state;
    String postalCode;
    String country;

    public Address(String street, String number, String complement, String neighborhood,
                   String city, String state, String postalCode, String country) {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Rua é obrigatória");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("Cidade é obrigatória");
        }
        if (state == null || state.isBlank()) {
            throw new IllegalArgumentException("Estado é obrigatório");
        }
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country == null || country.isBlank() ? "BR" : country;
    }

    public Address(String street, String number, String complement, String neighborhood,
                   String city, String state, String postalCode) {
        this(street, number, complement, neighborhood, city, state, postalCode, "BR");
    }
}

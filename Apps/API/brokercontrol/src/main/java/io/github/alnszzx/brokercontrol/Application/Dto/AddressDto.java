package io.github.alnszzx.brokercontrol.Application.Dto;

import jakarta.validation.constraints.NotBlank;

public record AddressDto(
        @NotBlank String street,
        String number,
        String complement,
        String neighborhood,
        @NotBlank String city,
        @NotBlank String state,
        String postalCode,
        String country
) {
}

package io.github.alnszzx.brokercontrol.Application.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateClientRequest(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String phoneNumber,
        @Valid AddressDto address
) {
}

package io.github.alnszzx.brokercontrol.Application.Dto;

import io.github.alnszzx.brokercontrol.Domain.Enum.PropertyStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePropertyRequest(
        @NotBlank String name,
        @Valid AddressDto address,
        String description,
        BigDecimal monthlyRent,
        PropertyStatus status,
        @NotNull UUID brokerId,
        UUID ownerId
) {
}

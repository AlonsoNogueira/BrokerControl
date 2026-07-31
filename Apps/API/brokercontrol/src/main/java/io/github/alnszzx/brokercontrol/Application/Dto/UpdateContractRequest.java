package io.github.alnszzx.brokercontrol.Application.Dto;

import io.github.alnszzx.brokercontrol.Domain.Enum.ContractStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record UpdateContractRequest(
        @NotBlank String title,
        String description,
        @NotNull UUID propertyId,
        @NotNull UUID ownerId,
        UUID tenantId,
        @NotNull UUID brokerId,
        BigDecimal rentAmount,
        LocalDate startDate,
        LocalDate endDate,
        ContractStatus status
) {
}

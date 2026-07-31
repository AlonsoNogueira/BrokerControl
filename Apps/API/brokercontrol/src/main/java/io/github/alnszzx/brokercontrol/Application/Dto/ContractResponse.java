package io.github.alnszzx.brokercontrol.Application.Dto;

import io.github.alnszzx.brokercontrol.Domain.Entity.Contract;
import io.github.alnszzx.brokercontrol.Domain.Enum.ContractStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record ContractResponse(
        UUID id,
        String title,
        String description,
        UUID propertyId,
        UUID ownerId,
        UUID tenantId,
        UUID brokerId,
        BigDecimal rentAmount,
        String currency,
        LocalDate startDate,
        LocalDate endDate,
        ContractStatus status,
        Instant createdAt,
        Instant updatedAt
) {

    public static ContractResponse from(Contract contract) {
        return new ContractResponse(
                contract.getId(),
                contract.getTitle(),
                contract.getDescription(),
                contract.getProperty() == null ? null : contract.getProperty().getId(),
                contract.getOwner() == null ? null : contract.getOwner().getId(),
                contract.getTenant() == null ? null : contract.getTenant().getId(),
                contract.getBroker() == null ? null : contract.getBroker().getId(),
                contract.getRentAmount() == null ? null : contract.getRentAmount().getAmount(),
                contract.getRentAmount() == null ? null : contract.getRentAmount().getCurrency().getCurrencyCode(),
                contract.getStartDate(),
                contract.getEndDate(),
                contract.getStatus(),
                contract.getCreatedAt(),
                contract.getUpdatedAt()
        );
    }
}

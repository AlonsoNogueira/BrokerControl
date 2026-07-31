package io.github.alnszzx.brokercontrol.Application.Dto;

import io.github.alnszzx.brokercontrol.Domain.Entity.Property;
import io.github.alnszzx.brokercontrol.Domain.Enum.PropertyStatus;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Address;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PropertyResponse(
        UUID id,
        String name,
        Address address,
        String description,
        BigDecimal monthlyRent,
        String currency,
        PropertyStatus status,
        UUID brokerId,
        UUID ownerId,
        Instant createdAt,
        Instant updatedAt
) {

    public static PropertyResponse from(Property property) {
        return new PropertyResponse(
                property.getId(),
                property.getName(),
                property.getAddress(),
                property.getDescription(),
                property.getMonthlyRent() == null ? null : property.getMonthlyRent().getAmount(),
                property.getMonthlyRent() == null ? null : property.getMonthlyRent().getCurrency().getCurrencyCode(),
                property.getStatus(),
                property.getBroker() == null ? null : property.getBroker().getId(),
                property.getOwner() == null ? null : property.getOwner().getId(),
                property.getCreatedAt(),
                property.getUpdatedAt()
        );
    }
}

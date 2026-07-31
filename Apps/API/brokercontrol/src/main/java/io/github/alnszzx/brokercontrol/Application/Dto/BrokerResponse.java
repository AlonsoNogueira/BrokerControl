package io.github.alnszzx.brokercontrol.Application.Dto;

import io.github.alnszzx.brokercontrol.Domain.Entity.Broker;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Address;

import java.time.Instant;
import java.util.UUID;

public record BrokerResponse(
        UUID id,
        String name,
        String email,
        String phoneNumber,
        Address address,
        Instant createdAt,
        Instant updatedAt
) {

    public static BrokerResponse from(Broker broker) {
        return new BrokerResponse(
                broker.getId(),
                broker.getName(),
                broker.getEmail() == null ? null : broker.getEmail().getValue(),
                broker.getPhoneNumber() == null ? null : broker.getPhoneNumber().getValue(),
                broker.getAddress(),
                broker.getCreatedAt(),
                broker.getUpdatedAt()
        );
    }
}

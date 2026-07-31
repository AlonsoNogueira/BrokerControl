package io.github.alnszzx.brokercontrol.Application.Dto;

import io.github.alnszzx.brokercontrol.Domain.Entity.Client;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Address;

import java.time.Instant;
import java.util.UUID;

public record ClientResponse(
        UUID id,
        String name,
        String email,
        String phoneNumber,
        Address address,
        Instant createdAt,
        Instant updatedAt
) {

    public static ClientResponse from(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getEmail() == null ? null : client.getEmail().getValue(),
                client.getPhoneNumber() == null ? null : client.getPhoneNumber().getValue(),
                client.getAddress(),
                client.getCreatedAt(),
                client.getUpdatedAt()
        );
    }
}

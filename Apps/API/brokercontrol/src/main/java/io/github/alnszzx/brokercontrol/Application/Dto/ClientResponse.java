package io.github.alnszzx.brokercontrol.Application.Dto;

import io.github.alnszzx.brokercontrol.Domain.Entity.Client;

public record ClientResponse(
        String name,
        String email
) {

    public static ClientResponse from(Client client) {
        return new ClientResponse(
                client.getName(),
                client.getEmail() == null ? null : client.getEmail().getValue()
        );
    }
}

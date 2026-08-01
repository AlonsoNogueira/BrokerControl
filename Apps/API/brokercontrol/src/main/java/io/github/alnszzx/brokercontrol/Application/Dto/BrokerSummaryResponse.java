package io.github.alnszzx.brokercontrol.Application.Dto;

import io.github.alnszzx.brokercontrol.Domain.Entity.Broker;

import java.util.UUID;

public record BrokerSummaryResponse(
        String name,
        String email
) {

    public static BrokerSummaryResponse from(Broker broker) {
        return new BrokerSummaryResponse(
                broker.getName(),
                broker.getEmail() == null ? null : broker.getEmail().getValue()
        );
    }
}

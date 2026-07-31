package io.github.alnszzx.brokercontrol.Application.Dto;

public record CreateBrokerRequest(
        String name,
        String email,
        String phoneNumber,
        AddressDto address
) {
}

package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Mapper;

import io.github.alnszzx.brokercontrol.Domain.Entity.Client;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.ClientJpaEntity;

public final class ClientMapper {

    private ClientMapper() {
    }

    public static ClientJpaEntity toJpaEntity(Client client) {
        if (client == null) {
            return null;
        }
        return ClientJpaEntity.builder()
                .id(client.getId())
                .name(client.getName())
                .email(ValueObjectMapper.toEmailJpa(client.getEmail()))
                .phoneNumber(ValueObjectMapper.toPhoneNumberJpa(client.getPhoneNumber()))
                .address(ValueObjectMapper.toAddressJpa(client.getAddress()))
                .createdAt(client.getCreatedAt())
                .updatedAt(client.getUpdatedAt())
                .build();
    }

    public static Client toDomain(ClientJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return Client.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(ValueObjectMapper.toEmail(entity.getEmail()))
                .phoneNumber(ValueObjectMapper.toPhoneNumber(entity.getPhoneNumber()))
                .address(ValueObjectMapper.toAddress(entity.getAddress()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}

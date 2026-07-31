package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Mapper;

import io.github.alnszzx.brokercontrol.Domain.Entity.Broker;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.BrokerJpaEntity;

public final class BrokerMapper {

    private BrokerMapper() {
    }

    public static BrokerJpaEntity toJpaEntity(Broker broker) {
        if (broker == null) {
            return null;
        }
        return BrokerJpaEntity.builder()
                .id(broker.getId())
                .name(broker.getName())
                .email(ValueObjectMapper.toEmailJpa(broker.getEmail()))
                .phoneNumber(ValueObjectMapper.toPhoneNumberJpa(broker.getPhoneNumber()))
                .address(ValueObjectMapper.toAddressJpa(broker.getAddress()))
                .createdAt(broker.getCreatedAt())
                .updatedAt(broker.getUpdatedAt())
                .build();
    }

    public static Broker toDomain(BrokerJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return Broker.builder()
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

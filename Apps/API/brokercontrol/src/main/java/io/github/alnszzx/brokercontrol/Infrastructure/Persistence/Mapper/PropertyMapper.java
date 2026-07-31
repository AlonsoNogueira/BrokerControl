package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Mapper;

import io.github.alnszzx.brokercontrol.Domain.Entity.Property;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.BrokerJpaEntity;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.ClientJpaEntity;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.PropertyJpaEntity;

public final class PropertyMapper {

    private PropertyMapper() {
    }

    public static PropertyJpaEntity toJpaEntity(Property property) {
        if (property == null) {
            return null;
        }
        return PropertyJpaEntity.builder()
                .id(property.getId())
                .name(property.getName())
                .address(ValueObjectMapper.toAddressJpa(property.getAddress()))
                .description(property.getDescription())
                .monthlyRent(ValueObjectMapper.toMoneyJpa(property.getMonthlyRent()))
                .status(property.getStatus())
                .broker(toBrokerReference(property))
                .owner(toOwnerReference(property))
                .createdAt(property.getCreatedAt())
                .updatedAt(property.getUpdatedAt())
                .build();
    }

    public static Property toDomain(PropertyJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return Property.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(ValueObjectMapper.toAddress(entity.getAddress()))
                .description(entity.getDescription())
                .monthlyRent(ValueObjectMapper.toMoney(entity.getMonthlyRent()))
                .status(entity.getStatus())
                .broker(BrokerMapper.toDomain(entity.getBroker()))
                .owner(ClientMapper.toDomain(entity.getOwner()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private static BrokerJpaEntity toBrokerReference(Property property) {
        if (property.getBroker() == null) {
            return null;
        }
        return BrokerJpaEntity.builder().id(property.getBroker().getId()).build();
    }

    private static ClientJpaEntity toOwnerReference(Property property) {
        if (property.getOwner() == null) {
            return null;
        }
        return ClientJpaEntity.builder().id(property.getOwner().getId()).build();
    }
}

package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Mapper;

import io.github.alnszzx.brokercontrol.Domain.Entity.Contract;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.BrokerJpaEntity;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.ClientJpaEntity;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.ContractJpaEntity;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.PropertyJpaEntity;

public final class ContractMapper {

    private ContractMapper() {
    }

    public static ContractJpaEntity toJpaEntity(Contract contract) {
        if (contract == null) {
            return null;
        }
        return ContractJpaEntity.builder()
                .id(contract.getId())
                .title(contract.getTitle())
                .description(contract.getDescription())
                .property(toPropertyReference(contract))
                .owner(toOwnerReference(contract))
                .tenant(toTenantReference(contract))
                .broker(toBrokerReference(contract))
                .rentAmount(ValueObjectMapper.toMoneyJpa(contract.getRentAmount()))
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate())
                .status(contract.getStatus())
                .createdAt(contract.getCreatedAt())
                .updatedAt(contract.getUpdatedAt())
                .build();
    }

    public static Contract toDomain(ContractJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return Contract.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .property(PropertyMapper.toDomain(entity.getProperty()))
                .owner(ClientMapper.toDomain(entity.getOwner()))
                .tenant(ClientMapper.toDomain(entity.getTenant()))
                .broker(BrokerMapper.toDomain(entity.getBroker()))
                .rentAmount(ValueObjectMapper.toMoney(entity.getRentAmount()))
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private static PropertyJpaEntity toPropertyReference(Contract contract) {
        if (contract.getProperty() == null) {
            return null;
        }
        return PropertyJpaEntity.builder().id(contract.getProperty().getId()).build();
    }

    private static ClientJpaEntity toOwnerReference(Contract contract) {
        if (contract.getOwner() == null) {
            return null;
        }
        return ClientJpaEntity.builder().id(contract.getOwner().getId()).build();
    }

    private static ClientJpaEntity toTenantReference(Contract contract) {
        if (contract.getTenant() == null) {
            return null;
        }
        return ClientJpaEntity.builder().id(contract.getTenant().getId()).build();
    }

    private static BrokerJpaEntity toBrokerReference(Contract contract) {
        if (contract.getBroker() == null) {
            return null;
        }
        return BrokerJpaEntity.builder().id(contract.getBroker().getId()).build();
    }
}

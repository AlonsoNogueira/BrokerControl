package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Repository;

import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.ContractJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContractSpringDataRepository extends JpaRepository<ContractJpaEntity, UUID> {

    @Override
    @EntityGraph(attributePaths = {"property", "property.broker", "property.owner", "owner", "tenant", "broker"})
    Optional<ContractJpaEntity> findById(UUID id);

    @Override
    @EntityGraph(attributePaths = {"property", "property.broker", "property.owner", "owner", "tenant", "broker"})
    List<ContractJpaEntity> findAll();
}

package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Repository;

import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.PropertyJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertySpringDataRepository extends JpaRepository<PropertyJpaEntity, UUID> {

    @Override
    @EntityGraph(attributePaths = {"broker", "owner"})
    Optional<PropertyJpaEntity> findById(UUID id);

    @Override
    @EntityGraph(attributePaths = {"broker", "owner"})
    List<PropertyJpaEntity> findAll();
}

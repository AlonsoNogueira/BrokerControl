package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Repository;

import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.BrokerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BrokerSpringDataRepository extends JpaRepository<BrokerJpaEntity, UUID> {

    boolean existsByEmail_Value(String email);
}

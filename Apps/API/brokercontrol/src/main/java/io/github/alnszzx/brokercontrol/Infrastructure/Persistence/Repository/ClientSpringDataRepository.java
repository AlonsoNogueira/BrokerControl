package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Repository;

import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.ClientJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientSpringDataRepository extends JpaRepository<ClientJpaEntity, UUID> {

    boolean existsByEmail_Value(String email);
}

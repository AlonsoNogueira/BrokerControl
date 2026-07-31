package io.github.alnszzx.brokercontrol.Domain.repository;

import io.github.alnszzx.brokercontrol.Domain.Entity.Broker;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrokerRepository {

    Broker save(Broker broker);

    Optional<Broker> findById(UUID id);

    List<Broker> findAll();

    boolean existsById(UUID id);

    boolean existsByEmail(String email);

    void deleteById(UUID id);
}

package io.github.alnszzx.brokercontrol.Domain.repository;

import io.github.alnszzx.brokercontrol.Domain.Entity.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {

    Client save(Client client);

    Optional<Client> findById(UUID id);

    List<Client> findAll();

    boolean existsById(UUID id);

    boolean existsByEmail(String email);

    void deleteById(UUID id);
}

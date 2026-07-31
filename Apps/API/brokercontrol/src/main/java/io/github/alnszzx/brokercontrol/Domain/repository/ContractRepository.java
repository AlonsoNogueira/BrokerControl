package io.github.alnszzx.brokercontrol.Domain.repository;

import io.github.alnszzx.brokercontrol.Domain.Entity.Contract;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContractRepository {

    Contract save(Contract contract);

    Optional<Contract> findById(UUID id);

    List<Contract> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}

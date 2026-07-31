package io.github.alnszzx.brokercontrol.Domain.repository;

import io.github.alnszzx.brokercontrol.Domain.Entity.Property;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertyRepository {

    Property save(Property property);

    Optional<Property> findById(UUID id);

    List<Property> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}

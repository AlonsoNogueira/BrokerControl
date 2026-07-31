package io.github.alnszzx.brokercontrol.Domain.Entity;

import io.github.alnszzx.brokercontrol.Domain.Enum.PropertyStatus;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Address;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    private UUID id;
    private String name;
    private Address address;
    private String description;
    private Money monthlyRent;
    private PropertyStatus status;
    private Broker broker;
    private Client owner;
    private Instant createdAt;
    private Instant updatedAt;

    @Builder.Default
    private Set<Contract> contracts = new HashSet<>();
}

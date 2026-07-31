package io.github.alnszzx.brokercontrol.Domain.Entity;

import io.github.alnszzx.brokercontrol.Domain.ValueObject.Address;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Email;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.PhoneNumber;
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
public class Client {
    private UUID id;
    private String name;
    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private Instant createdAt;
    private Instant updatedAt;

    @Builder.Default
    private Set<Property> ownedProperties = new HashSet<>();

    @Builder.Default
    private Set<Property> rentedProperties = new HashSet<>();

    @Builder.Default
    private Set<Contract> contracts = new HashSet<>();
}

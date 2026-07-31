package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity;

import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.ValueObject.AddressJpa;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.ValueObject.EmailJpa;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.ValueObject.PhoneNumberJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "brokers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrokerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private EmailJpa email;

    @Embedded
    private PhoneNumberJpa phoneNumber;

    @Embedded
    private AddressJpa address;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "broker", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<PropertyJpaEntity> managedProperties = new HashSet<>();

    @OneToMany(mappedBy = "broker", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ContractJpaEntity> intermediatedContracts = new HashSet<>();
}

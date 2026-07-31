package io.github.alnszzx.brokercontrol.Domain.Entity;

import io.github.alnszzx.brokercontrol.Domain.Enum.ContractStatus;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    private UUID id;
    private String title;
    private String description;
    private Property property;
    private Client owner;
    private Client tenant;
    private Broker broker;
    private Money rentAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private ContractStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}

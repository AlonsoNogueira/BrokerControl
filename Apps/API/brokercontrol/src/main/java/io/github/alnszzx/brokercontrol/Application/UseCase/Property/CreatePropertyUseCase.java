package io.github.alnszzx.brokercontrol.Application.UseCase.Property;

import io.github.alnszzx.brokercontrol.Application.Dto.CreatePropertyRequest;
import io.github.alnszzx.brokercontrol.Application.Dto.PropertyResponse;
import io.github.alnszzx.brokercontrol.Application.Support.AddressMapper;
import io.github.alnszzx.brokercontrol.Domain.Entity.Broker;
import io.github.alnszzx.brokercontrol.Domain.Entity.Client;
import io.github.alnszzx.brokercontrol.Domain.Entity.Property;
import io.github.alnszzx.brokercontrol.Domain.Enum.PropertyStatus;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Money;
import io.github.alnszzx.brokercontrol.Domain.repository.BrokerRepository;
import io.github.alnszzx.brokercontrol.Domain.repository.ClientRepository;
import io.github.alnszzx.brokercontrol.Domain.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePropertyUseCase {

    private final PropertyRepository propertyRepository;
    private final BrokerRepository brokerRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public PropertyResponse execute(CreatePropertyRequest request) {
        if (request.name() == null || request.name().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        Broker broker = brokerRepository.findById(request.brokerId())
                .orElseThrow(() -> new IllegalArgumentException("Corretor não encontrado"));
        Client owner = request.ownerId() == null ? null
                : clientRepository.findById(request.ownerId())
                        .orElseThrow(() -> new IllegalArgumentException("Proprietário não encontrado"));

        Property property = Property.builder()
                .id(UUID.randomUUID())
                .name(request.name().trim())
                .address(AddressMapper.toAddress(request.address()))
                .description(request.description())
                .monthlyRent(request.monthlyRent() == null ? null : Money.real(request.monthlyRent()))
                .status(request.status() == null ? PropertyStatus.AVAILABLE : request.status())
                .broker(broker)
                .owner(owner)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        return PropertyResponse.from(propertyRepository.save(property));
    }
}

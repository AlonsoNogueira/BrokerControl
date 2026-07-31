package io.github.alnszzx.brokercontrol.Application.UseCase.Property;

import io.github.alnszzx.brokercontrol.Application.Dto.PropertyResponse;
import io.github.alnszzx.brokercontrol.Application.Dto.UpdatePropertyRequest;
import io.github.alnszzx.brokercontrol.Application.Support.AddressMapper;
import io.github.alnszzx.brokercontrol.Domain.Entity.Broker;
import io.github.alnszzx.brokercontrol.Domain.Entity.Client;
import io.github.alnszzx.brokercontrol.Domain.Entity.Property;
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
public class UpdatePropertyUseCase {

    private final PropertyRepository propertyRepository;
    private final BrokerRepository brokerRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public PropertyResponse execute(UUID id, UpdatePropertyRequest request) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Imóvel não encontrado"));

        Broker broker = brokerRepository.findById(request.brokerId())
                .orElseThrow(() -> new IllegalArgumentException("Corretor não encontrado"));
        Client owner = request.ownerId() == null ? null
                : clientRepository.findById(request.ownerId())
                        .orElseThrow(() -> new IllegalArgumentException("Proprietário não encontrado"));

        property.setName(request.name().trim());
        property.setAddress(AddressMapper.toAddress(request.address()));
        property.setDescription(request.description());
        property.setMonthlyRent(request.monthlyRent() == null ? null : Money.real(request.monthlyRent()));
        property.setStatus(request.status());
        property.setBroker(broker);
        property.setOwner(owner);
        property.setUpdatedAt(Instant.now());

        return PropertyResponse.from(propertyRepository.save(property));
    }
}

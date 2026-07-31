package io.github.alnszzx.brokercontrol.Application.UseCase.Contract;

import io.github.alnszzx.brokercontrol.Application.Dto.ContractResponse;
import io.github.alnszzx.brokercontrol.Application.Dto.CreateContractRequest;
import io.github.alnszzx.brokercontrol.Domain.Entity.Broker;
import io.github.alnszzx.brokercontrol.Domain.Entity.Client;
import io.github.alnszzx.brokercontrol.Domain.Entity.Contract;
import io.github.alnszzx.brokercontrol.Domain.Entity.Property;
import io.github.alnszzx.brokercontrol.Domain.Enum.ContractStatus;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Money;
import io.github.alnszzx.brokercontrol.Domain.repository.BrokerRepository;
import io.github.alnszzx.brokercontrol.Domain.repository.ClientRepository;
import io.github.alnszzx.brokercontrol.Domain.repository.ContractRepository;
import io.github.alnszzx.brokercontrol.Domain.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateContractUseCase {

    private final ContractRepository contractRepository;
    private final PropertyRepository propertyRepository;
    private final ClientRepository clientRepository;
    private final BrokerRepository brokerRepository;

    @Transactional
    public ContractResponse execute(CreateContractRequest request) {
        Property property = propertyRepository.findById(request.propertyId())
                .orElseThrow(() -> new IllegalArgumentException("Imóvel não encontrado"));
        Client owner = clientRepository.findById(request.ownerId())
                .orElseThrow(() -> new IllegalArgumentException("Proprietário não encontrado"));
        Client tenant = request.tenantId() == null ? null
                : clientRepository.findById(request.tenantId())
                        .orElseThrow(() -> new IllegalArgumentException("Inquilino não encontrado"));
        Broker broker = brokerRepository.findById(request.brokerId())
                .orElseThrow(() -> new IllegalArgumentException("Corretor não encontrado"));

        Contract contract = Contract.builder()
                .id(UUID.randomUUID())
                .title(request.title().trim())
                .description(request.description())
                .property(property)
                .owner(owner)
                .tenant(tenant)
                .broker(broker)
                .rentAmount(request.rentAmount() == null ? null : Money.real(request.rentAmount()))
                .startDate(request.startDate())
                .endDate(request.endDate())
                .status(request.status() == null ? ContractStatus.PENDING : request.status())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        return ContractResponse.from(contractRepository.save(contract));
    }
}

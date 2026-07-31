package io.github.alnszzx.brokercontrol.Application.UseCase.Contract;

import io.github.alnszzx.brokercontrol.Application.Dto.ContractResponse;
import io.github.alnszzx.brokercontrol.Application.Dto.UpdateContractRequest;
import io.github.alnszzx.brokercontrol.Domain.Entity.Broker;
import io.github.alnszzx.brokercontrol.Domain.Entity.Client;
import io.github.alnszzx.brokercontrol.Domain.Entity.Contract;
import io.github.alnszzx.brokercontrol.Domain.Entity.Property;
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
public class UpdateContractUseCase {

    private final ContractRepository contractRepository;
    private final PropertyRepository propertyRepository;
    private final ClientRepository clientRepository;
    private final BrokerRepository brokerRepository;

    @Transactional
    public ContractResponse execute(UUID id, UpdateContractRequest request) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado"));

        Property property = propertyRepository.findById(request.propertyId())
                .orElseThrow(() -> new IllegalArgumentException("Imóvel não encontrado"));
        Client owner = clientRepository.findById(request.ownerId())
                .orElseThrow(() -> new IllegalArgumentException("Proprietário não encontrado"));
        Client tenant = request.tenantId() == null ? null
                : clientRepository.findById(request.tenantId())
                        .orElseThrow(() -> new IllegalArgumentException("Inquilino não encontrado"));
        Broker broker = brokerRepository.findById(request.brokerId())
                .orElseThrow(() -> new IllegalArgumentException("Corretor não encontrado"));

        contract.setTitle(request.title().trim());
        contract.setDescription(request.description());
        contract.setProperty(property);
        contract.setOwner(owner);
        contract.setTenant(tenant);
        contract.setBroker(broker);
        contract.setRentAmount(request.rentAmount() == null ? null : Money.real(request.rentAmount()));
        contract.setStartDate(request.startDate());
        contract.setEndDate(request.endDate());
        contract.setStatus(request.status());
        contract.setUpdatedAt(Instant.now());

        return ContractResponse.from(contractRepository.save(contract));
    }
}

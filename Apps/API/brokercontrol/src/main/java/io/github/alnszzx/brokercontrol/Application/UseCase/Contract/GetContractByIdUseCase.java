package io.github.alnszzx.brokercontrol.Application.UseCase.Contract;

import io.github.alnszzx.brokercontrol.Application.Dto.ContractResponse;
import io.github.alnszzx.brokercontrol.Domain.Entity.Contract;
import io.github.alnszzx.brokercontrol.Domain.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetContractByIdUseCase {

    private final ContractRepository contractRepository;

    public ContractResponse execute(UUID id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado"));
        return ContractResponse.from(contract);
    }
}

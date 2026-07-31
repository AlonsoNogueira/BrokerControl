package io.github.alnszzx.brokercontrol.Application.UseCase.Contract;

import io.github.alnszzx.brokercontrol.Domain.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteContractUseCase {

    private final ContractRepository contractRepository;

    @Transactional
    public void execute(UUID id) {
        if (!contractRepository.existsById(id)) {
            throw new IllegalArgumentException("Contrato não encontrado");
        }
        contractRepository.deleteById(id);
    }
}

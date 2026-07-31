package io.github.alnszzx.brokercontrol.Application.UseCase.Contract;

import io.github.alnszzx.brokercontrol.Application.Dto.ContractResponse;
import io.github.alnszzx.brokercontrol.Domain.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListContractsUseCase {

    private final ContractRepository contractRepository;

    public List<ContractResponse> execute() {
        return contractRepository.findAll().stream()
                .map(ContractResponse::from)
                .toList();
    }
}

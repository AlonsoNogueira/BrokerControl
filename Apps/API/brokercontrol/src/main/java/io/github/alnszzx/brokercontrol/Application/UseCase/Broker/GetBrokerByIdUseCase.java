package io.github.alnszzx.brokercontrol.Application.UseCase.Broker;

import io.github.alnszzx.brokercontrol.Application.Dto.BrokerSummaryResponse;
import io.github.alnszzx.brokercontrol.Application.Exception.NotFoundException;
import io.github.alnszzx.brokercontrol.Domain.Entity.Broker;
import io.github.alnszzx.brokercontrol.Domain.repository.BrokerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetBrokerByIdUseCase {

    private final BrokerRepository brokerRepository;

    public BrokerSummaryResponse execute(UUID id) {
        Broker broker = brokerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Corretor não encontrado"));
        return BrokerSummaryResponse.from(broker);
    }
}

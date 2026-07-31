package io.github.alnszzx.brokercontrol.Application.UseCase.Broker;

import io.github.alnszzx.brokercontrol.Application.Dto.BrokerResponse;
import io.github.alnszzx.brokercontrol.Domain.Entity.Broker;
import io.github.alnszzx.brokercontrol.Domain.repository.BrokerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetBrokerByIdUseCase {

    private final BrokerRepository brokerRepository;

    public BrokerResponse execute(UUID id) {
        Broker broker = brokerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Corretor não encontrado"));
        return BrokerResponse.from(broker);
    }
}

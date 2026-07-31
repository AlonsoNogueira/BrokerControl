package io.github.alnszzx.brokercontrol.Application.UseCase.Broker;

import io.github.alnszzx.brokercontrol.Application.Dto.BrokerResponse;
import io.github.alnszzx.brokercontrol.Domain.repository.BrokerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListBrokersUseCase {

    private final BrokerRepository brokerRepository;

    public List<BrokerResponse> execute() {
        return brokerRepository.findAll().stream()
                .map(BrokerResponse::from)
                .toList();
    }
}

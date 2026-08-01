package io.github.alnszzx.brokercontrol.Application.UseCase.Broker;

import io.github.alnszzx.brokercontrol.Application.Dto.BrokerSummaryResponse;
import io.github.alnszzx.brokercontrol.Domain.repository.BrokerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListBrokersUseCase {

    private final BrokerRepository brokerRepository;

    public List<BrokerSummaryResponse> execute() {
        return brokerRepository.findAll().stream()
                .map(BrokerSummaryResponse::from)
                .toList();
    }
}

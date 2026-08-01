package io.github.alnszzx.brokercontrol.Application.UseCase.Broker;

import io.github.alnszzx.brokercontrol.Application.Exception.NotFoundException;
import io.github.alnszzx.brokercontrol.Domain.repository.BrokerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteBrokerUseCase {

    private final BrokerRepository brokerRepository;

    @Transactional
    public void execute(UUID id) {
        if (!brokerRepository.existsById(id)) {
            throw new NotFoundException("Corretor não encontrado");
        }
        brokerRepository.deleteById(id);
    }
}

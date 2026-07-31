package io.github.alnszzx.brokercontrol.Application.UseCase.Client;

import io.github.alnszzx.brokercontrol.Domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteClientUseCase {

    private final ClientRepository clientRepository;

    @Transactional
    public void execute(UUID id) {
        if (!clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        clientRepository.deleteById(id);
    }
}

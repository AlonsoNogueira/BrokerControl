package io.github.alnszzx.brokercontrol.Application.UseCase.Client;

import io.github.alnszzx.brokercontrol.Application.Dto.ClientResponse;
import io.github.alnszzx.brokercontrol.Domain.Entity.Client;
import io.github.alnszzx.brokercontrol.Domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetClientByIdUseCase {

    private final ClientRepository clientRepository;

    public ClientResponse execute(UUID id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        return ClientResponse.from(client);
    }
}

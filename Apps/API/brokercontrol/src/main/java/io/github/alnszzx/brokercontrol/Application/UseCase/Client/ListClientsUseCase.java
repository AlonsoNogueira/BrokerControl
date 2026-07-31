package io.github.alnszzx.brokercontrol.Application.UseCase.Client;

import io.github.alnszzx.brokercontrol.Application.Dto.ClientResponse;
import io.github.alnszzx.brokercontrol.Domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListClientsUseCase {

    private final ClientRepository clientRepository;

    public List<ClientResponse> execute() {
        return clientRepository.findAll().stream()
                .map(ClientResponse::from)
                .toList();
    }
}

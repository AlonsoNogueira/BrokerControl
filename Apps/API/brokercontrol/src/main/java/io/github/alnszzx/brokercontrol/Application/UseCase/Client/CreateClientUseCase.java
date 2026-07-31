package io.github.alnszzx.brokercontrol.Application.UseCase.Client;

import io.github.alnszzx.brokercontrol.Application.Dto.ClientResponse;
import io.github.alnszzx.brokercontrol.Application.Dto.CreateClientRequest;
import io.github.alnszzx.brokercontrol.Application.Support.AddressMapper;
import io.github.alnszzx.brokercontrol.Domain.Entity.Client;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Email;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.PhoneNumber;
import io.github.alnszzx.brokercontrol.Domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateClientUseCase {

    private final ClientRepository clientRepository;

    @Transactional
    public ClientResponse execute(CreateClientRequest request) {
        if (request.name() == null || request.name().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        String normalizedEmail = request.email() == null ? null : request.email().trim().toLowerCase();
        if (clientRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com o e-mail informado");
        }

        Client client = Client.builder()
                .id(UUID.randomUUID())
                .name(request.name().trim())
                .email(new Email(request.email()))
                .phoneNumber(new PhoneNumber(request.phoneNumber()))
                .address(AddressMapper.toAddress(request.address()))
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        return ClientResponse.from(clientRepository.save(client));
    }
}

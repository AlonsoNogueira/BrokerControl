package io.github.alnszzx.brokercontrol.Application.UseCase.Client;

import io.github.alnszzx.brokercontrol.Application.Dto.ClientResponse;
import io.github.alnszzx.brokercontrol.Application.Dto.UpdateClientRequest;
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
public class UpdateClientUseCase {

    private final ClientRepository clientRepository;

    @Transactional
    public ClientResponse execute(UUID id, UpdateClientRequest request) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        client.setName(request.name().trim());
        client.setEmail(new Email(request.email()));
        client.setPhoneNumber(new PhoneNumber(request.phoneNumber()));
        client.setAddress(AddressMapper.toAddress(request.address()));
        client.setUpdatedAt(Instant.now());

        return ClientResponse.from(clientRepository.save(client));
    }
}

package io.github.alnszzx.brokercontrol.Application.UseCase.Broker;

import io.github.alnszzx.brokercontrol.Application.Dto.BrokerSummaryResponse;
import io.github.alnszzx.brokercontrol.Application.Dto.CreateBrokerRequest;
import io.github.alnszzx.brokercontrol.Application.Support.AddressMapper;
import io.github.alnszzx.brokercontrol.Domain.Entity.Broker;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Email;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.PhoneNumber;
import io.github.alnszzx.brokercontrol.Domain.repository.BrokerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateBrokerUseCase {

    private final BrokerRepository brokerRepository;

    @Transactional
    public BrokerSummaryResponse execute(CreateBrokerRequest input) {
        if (input.name() == null || input.name().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        String normalizedEmail = input.email() == null ? null : input.email().trim().toLowerCase();
        if (brokerRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("Já existe um corretor cadastrado com o e-mail informado");
        }

        Broker broker = Broker.builder()
                .id(UUID.randomUUID())
                .name(input.name().trim())
                .email(new Email(input.email()))
                .phoneNumber(new PhoneNumber(input.phoneNumber()))
                .address(AddressMapper.toAddress(input.address()))
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        Broker saved = brokerRepository.save(broker);
        return BrokerSummaryResponse.from(saved);
    }
}

package io.github.alnszzx.brokercontrol.Application.UseCase.Broker;

import io.github.alnszzx.brokercontrol.Application.Dto.BrokerSummaryResponse;
import io.github.alnszzx.brokercontrol.Application.Dto.UpdateBrokerRequest;
import io.github.alnszzx.brokercontrol.Application.Exception.NotFoundException;
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
public class UpdateBrokerUseCase {

    private final BrokerRepository brokerRepository;

    @Transactional
    public BrokerSummaryResponse execute(UUID id, UpdateBrokerRequest request) {
        Broker broker = brokerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Corretor não encontrado"));

        broker.setName(request.name().trim());
        broker.setEmail(new Email(request.email()));
        broker.setPhoneNumber(new PhoneNumber(request.phoneNumber()));
        broker.setAddress(AddressMapper.toAddress(request.address()));
        broker.setUpdatedAt(Instant.now());

        return BrokerSummaryResponse.from(brokerRepository.save(broker));
    }
}

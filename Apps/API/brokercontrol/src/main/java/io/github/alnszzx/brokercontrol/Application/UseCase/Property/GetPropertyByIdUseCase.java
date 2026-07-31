package io.github.alnszzx.brokercontrol.Application.UseCase.Property;

import io.github.alnszzx.brokercontrol.Application.Dto.PropertyResponse;
import io.github.alnszzx.brokercontrol.Domain.Entity.Property;
import io.github.alnszzx.brokercontrol.Domain.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetPropertyByIdUseCase {

    private final PropertyRepository propertyRepository;

    public PropertyResponse execute(UUID id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Imóvel não encontrado"));
        return PropertyResponse.from(property);
    }
}

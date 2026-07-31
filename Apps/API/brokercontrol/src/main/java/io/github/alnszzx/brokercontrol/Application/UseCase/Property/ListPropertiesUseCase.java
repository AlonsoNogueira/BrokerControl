package io.github.alnszzx.brokercontrol.Application.UseCase.Property;

import io.github.alnszzx.brokercontrol.Application.Dto.PropertyResponse;
import io.github.alnszzx.brokercontrol.Domain.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListPropertiesUseCase {

    private final PropertyRepository propertyRepository;

    public List<PropertyResponse> execute() {
        return propertyRepository.findAll().stream()
                .map(PropertyResponse::from)
                .toList();
    }
}

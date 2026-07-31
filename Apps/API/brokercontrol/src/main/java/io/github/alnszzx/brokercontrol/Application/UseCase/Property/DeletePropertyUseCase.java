package io.github.alnszzx.brokercontrol.Application.UseCase.Property;

import io.github.alnszzx.brokercontrol.Domain.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletePropertyUseCase {

    private final PropertyRepository propertyRepository;

    @Transactional
    public void execute(UUID id) {
        if (!propertyRepository.existsById(id)) {
            throw new IllegalArgumentException("Imóvel não encontrado");
        }
        propertyRepository.deleteById(id);
    }
}

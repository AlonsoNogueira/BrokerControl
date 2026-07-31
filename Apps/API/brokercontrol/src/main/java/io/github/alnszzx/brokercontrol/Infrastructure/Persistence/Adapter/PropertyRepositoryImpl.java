package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Adapter;

import io.github.alnszzx.brokercontrol.Domain.Entity.Property;
import io.github.alnszzx.brokercontrol.Domain.repository.PropertyRepository;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.PropertyJpaEntity;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Mapper.PropertyMapper;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Repository.PropertySpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PropertyRepositoryImpl implements PropertyRepository {

    private final PropertySpringDataRepository springDataRepository;

    @Override
    @Transactional
    public Property save(Property property) {
        PropertyJpaEntity entity = PropertyMapper.toJpaEntity(property);
        if (entity.getId() == null || !springDataRepository.existsById(entity.getId())) {
            entity.setId(null);
        }
        return PropertyMapper.toDomain(springDataRepository.save(entity));
    }

    @Override
    public Optional<Property> findById(UUID id) {
        return springDataRepository.findById(id).map(PropertyMapper::toDomain);
    }

    @Override
    public List<Property> findAll() {
        return springDataRepository.findAll().stream()
                .map(PropertyMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(UUID id) {
        return springDataRepository.existsById(id);
    }

    @Override
    public void deleteById(UUID id) {
        springDataRepository.deleteById(id);
    }
}

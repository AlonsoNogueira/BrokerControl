package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Adapter;

import io.github.alnszzx.brokercontrol.Domain.Entity.Broker;
import io.github.alnszzx.brokercontrol.Domain.repository.BrokerRepository;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.BrokerJpaEntity;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Mapper.BrokerMapper;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Repository.BrokerSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BrokerRepositoryImpl implements BrokerRepository {

    private final BrokerSpringDataRepository springDataRepository;

    @Override
    @Transactional
    public Broker save(Broker broker) {
        BrokerJpaEntity entity = BrokerMapper.toJpaEntity(broker);
        if (entity.getId() == null || !springDataRepository.existsById(entity.getId())) {
            entity.setId(null);
        }
        return BrokerMapper.toDomain(springDataRepository.save(entity));
    }

    @Override
    public Optional<Broker> findById(UUID id) {
        return springDataRepository.findById(id).map(BrokerMapper::toDomain);
    }

    @Override
    public List<Broker> findAll() {
        return springDataRepository.findAll().stream()
                .map(BrokerMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(UUID id) {
        return springDataRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return springDataRepository.existsByEmail_Value(email);
    }

    @Override
    public void deleteById(UUID id) {
        springDataRepository.deleteById(id);
    }
}

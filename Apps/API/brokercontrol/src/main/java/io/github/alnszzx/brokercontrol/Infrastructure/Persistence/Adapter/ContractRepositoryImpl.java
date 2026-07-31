package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Adapter;

import io.github.alnszzx.brokercontrol.Domain.Entity.Contract;
import io.github.alnszzx.brokercontrol.Domain.repository.ContractRepository;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.ContractJpaEntity;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Mapper.ContractMapper;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Repository.ContractSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ContractRepositoryImpl implements ContractRepository {

    private final ContractSpringDataRepository springDataRepository;

    @Override
    @Transactional
    public Contract save(Contract contract) {
        ContractJpaEntity entity = ContractMapper.toJpaEntity(contract);
        if (entity.getId() == null || !springDataRepository.existsById(entity.getId())) {
            entity.setId(null);
        }
        return ContractMapper.toDomain(springDataRepository.save(entity));
    }

    @Override
    public Optional<Contract> findById(UUID id) {
        return springDataRepository.findById(id).map(ContractMapper::toDomain);
    }

    @Override
    public List<Contract> findAll() {
        return springDataRepository.findAll().stream()
                .map(ContractMapper::toDomain)
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

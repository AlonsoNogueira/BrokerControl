package io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Adapter;

import io.github.alnszzx.brokercontrol.Domain.Entity.Client;
import io.github.alnszzx.brokercontrol.Domain.repository.ClientRepository;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Entity.ClientJpaEntity;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Mapper.ClientMapper;
import io.github.alnszzx.brokercontrol.Infrastructure.Persistence.Repository.ClientSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientSpringDataRepository springDataRepository;

    @Override
    @Transactional
    public Client save(Client client) {
        ClientJpaEntity entity = ClientMapper.toJpaEntity(client);
        if (entity.getId() == null || !springDataRepository.existsById(entity.getId())) {
            entity.setId(null);
        }
        return ClientMapper.toDomain(springDataRepository.save(entity));
    }

    @Override
    public Optional<Client> findById(UUID id) {
        return springDataRepository.findById(id).map(ClientMapper::toDomain);
    }

    @Override
    public List<Client> findAll() {
        return springDataRepository.findAll().stream()
                .map(ClientMapper::toDomain)
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

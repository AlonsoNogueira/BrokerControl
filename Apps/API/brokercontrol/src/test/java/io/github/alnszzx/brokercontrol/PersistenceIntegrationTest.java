package io.github.alnszzx.brokercontrol;

import io.github.alnszzx.brokercontrol.Domain.Entity.Broker;
import io.github.alnszzx.brokercontrol.Domain.Entity.Client;
import io.github.alnszzx.brokercontrol.Domain.Entity.Contract;
import io.github.alnszzx.brokercontrol.Domain.Entity.Property;
import io.github.alnszzx.brokercontrol.Domain.Enum.ContractStatus;
import io.github.alnszzx.brokercontrol.Domain.Enum.PropertyStatus;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Address;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Email;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.Money;
import io.github.alnszzx.brokercontrol.Domain.ValueObject.PhoneNumber;
import io.github.alnszzx.brokercontrol.Domain.repository.BrokerRepository;
import io.github.alnszzx.brokercontrol.Domain.repository.ClientRepository;
import io.github.alnszzx.brokercontrol.Domain.repository.ContractRepository;
import io.github.alnszzx.brokercontrol.Domain.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PersistenceIntegrationTest {

    @Autowired
    private BrokerRepository brokerRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Test
    void fullCrudFlowThroughDomainRepositories() {
        String brokerEmail = "broker." + UUID.randomUUID() + "@example.com";
        String clientEmail = "client." + UUID.randomUUID() + "@example.com";

        Broker broker = Broker.builder()
                .id(UUID.randomUUID())
                .name("Corretor A")
                .email(new Email(brokerEmail))
                .phoneNumber(new PhoneNumber("11999999999"))
                .address(new Address("Rua A", "100", null, "Centro", "Sao Paulo", "SP", "01000-000", "BR"))
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        Broker savedBroker = brokerRepository.save(broker);
        assertNotNull(savedBroker.getId());
        assertTrue(brokerRepository.existsById(savedBroker.getId()));
        assertTrue(brokerRepository.existsByEmail(brokerEmail));
        assertEquals("Corretor A", brokerRepository.findById(savedBroker.getId()).orElseThrow().getName());

        Client client = Client.builder()
                .id(UUID.randomUUID())
                .name("Cliente A")
                .email(new Email(clientEmail))
                .phoneNumber(new PhoneNumber("11988888888"))
                .address(new Address("Rua B", "200", null, "Centro", "Sao Paulo", "SP", "01000-001", "BR"))
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        Client savedClient = clientRepository.save(client);
        assertNotNull(savedClient.getId());
        assertTrue(clientRepository.existsByEmail(clientEmail));

        Property property = Property.builder()
                .id(UUID.randomUUID())
                .name("Casa A")
                .address(new Address("Rua C", "300", null, "Jardins", "Sao Paulo", "SP", "01000-002", "BR"))
                .description("Casa com 3 quartos")
                .monthlyRent(Money.real(new BigDecimal("3500.00")))
                .status(PropertyStatus.AVAILABLE)
                .broker(savedBroker)
                .owner(savedClient)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        Property savedProperty = propertyRepository.save(property);
        assertNotNull(savedProperty.getId());
        assertEquals(savedBroker.getId(), savedProperty.getBroker().getId());
        assertEquals(savedClient.getId(), savedProperty.getOwner().getId());

        Contract contract = Contract.builder()
                .id(UUID.randomUUID())
                .title("Contrato de Aluguel")
                .description("Aluguel residencial")
                .property(savedProperty)
                .owner(savedClient)
                .tenant(null)
                .broker(savedBroker)
                .rentAmount(Money.real(new BigDecimal("3500.00")))
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .status(ContractStatus.PENDING)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        Contract savedContract = contractRepository.save(contract);
        assertNotNull(savedContract.getId());
        assertEquals(savedProperty.getId(), savedContract.getProperty().getId());
        assertEquals(savedClient.getId(), savedContract.getOwner().getId());
        assertEquals(savedBroker.getId(), savedContract.getBroker().getId());

        Property loadedProperty = propertyRepository.findById(savedProperty.getId()).orElseThrow();
        assertEquals(savedBroker.getId(), loadedProperty.getBroker().getId());
        assertEquals(savedClient.getId(), loadedProperty.getOwner().getId());

        Contract loadedContract = contractRepository.findById(savedContract.getId()).orElseThrow();
        assertEquals(savedProperty.getId(), loadedContract.getProperty().getId());
        assertEquals(savedBroker.getId(), loadedContract.getBroker().getId());

        assertEquals(1, propertyRepository.findAll().size());
        assertEquals(1, contractRepository.findAll().size());

        savedBroker.setName("Corretor A Atualizado");
        savedBroker.setUpdatedAt(Instant.now());
        Broker updatedBroker = brokerRepository.save(savedBroker);
        assertEquals("Corretor A Atualizado", updatedBroker.getName());

        contractRepository.deleteById(savedContract.getId());
        propertyRepository.deleteById(savedProperty.getId());
        clientRepository.deleteById(savedClient.getId());
        brokerRepository.deleteById(savedBroker.getId());

        assertFalse(brokerRepository.existsById(savedBroker.getId()));
        assertFalse(clientRepository.existsById(savedClient.getId()));
        assertFalse(propertyRepository.existsById(savedProperty.getId()));
        assertFalse(contractRepository.existsById(savedContract.getId()));
    }
}

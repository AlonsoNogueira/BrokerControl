package io.github.alnszzx.brokercontrol.Interface.Controller;

import io.github.alnszzx.brokercontrol.Application.Dto.BrokerSummaryResponse;
import io.github.alnszzx.brokercontrol.Application.Dto.CreateBrokerRequest;
import io.github.alnszzx.brokercontrol.Application.Dto.UpdateBrokerRequest;
import io.github.alnszzx.brokercontrol.Application.UseCase.Broker.CreateBrokerUseCase;
import io.github.alnszzx.brokercontrol.Application.UseCase.Broker.DeleteBrokerUseCase;
import io.github.alnszzx.brokercontrol.Application.UseCase.Broker.GetBrokerByIdUseCase;
import io.github.alnszzx.brokercontrol.Application.UseCase.Broker.ListBrokersUseCase;
import io.github.alnszzx.brokercontrol.Application.UseCase.Broker.UpdateBrokerUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/brokers")
@RequiredArgsConstructor
public class BrokerController {

    private final CreateBrokerUseCase createBrokerUseCase;
    private final GetBrokerByIdUseCase getBrokerByIdUseCase;
    private final ListBrokersUseCase listBrokersUseCase;
    private final UpdateBrokerUseCase updateBrokerUseCase;
    private final DeleteBrokerUseCase deleteBrokerUseCase;

    @GetMapping
    public List<BrokerSummaryResponse> list() {
        return listBrokersUseCase.execute();
    }

    @GetMapping("/{id}")
    public BrokerSummaryResponse getById(@PathVariable UUID id) {
        return getBrokerByIdUseCase.execute(id);
    }

    @PostMapping
    public ResponseEntity<BrokerSummaryResponse> create(@Valid @RequestBody CreateBrokerRequest request) {
        BrokerSummaryResponse created = createBrokerUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public BrokerSummaryResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateBrokerRequest request) {
        return updateBrokerUseCase.execute(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteBrokerUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}

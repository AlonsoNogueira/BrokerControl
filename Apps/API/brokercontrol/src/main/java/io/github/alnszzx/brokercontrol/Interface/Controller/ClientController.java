package io.github.alnszzx.brokercontrol.Interface.Controller;

import io.github.alnszzx.brokercontrol.Application.Dto.ClientResponse;
import io.github.alnszzx.brokercontrol.Application.Dto.CreateClientRequest;
import io.github.alnszzx.brokercontrol.Application.Dto.UpdateClientRequest;
import io.github.alnszzx.brokercontrol.Application.UseCase.Client.CreateClientUseCase;
import io.github.alnszzx.brokercontrol.Application.UseCase.Client.DeleteClientUseCase;
import io.github.alnszzx.brokercontrol.Application.UseCase.Client.GetClientByIdUseCase;
import io.github.alnszzx.brokercontrol.Application.UseCase.Client.ListClientsUseCase;
import io.github.alnszzx.brokercontrol.Application.UseCase.Client.UpdateClientUseCase;
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
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final CreateClientUseCase createClientUseCase;
    private final GetClientByIdUseCase getClientByIdUseCase;
    private final ListClientsUseCase listClientsUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;

    @GetMapping
    public List<ClientResponse> list() {
        return listClientsUseCase.execute();
    }

    @GetMapping("/{id}")
    public ClientResponse getById(@PathVariable UUID id) {
        return getClientByIdUseCase.execute(id);
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody CreateClientRequest request) {
        ClientResponse created = createClientUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ClientResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateClientRequest request) {
        return updateClientUseCase.execute(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteClientUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}

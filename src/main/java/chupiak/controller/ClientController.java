package chupiak.controller;

import chupiak.model.Client;
import chupiak.model.dto.request.ClientRequestDto;
import chupiak.model.dto.response.ClientResponseDto;
import chupiak.service.ClientService;
import chupiak.service.dto.mapping.DtoRequestMapper;
import chupiak.service.dto.mapping.DtoResponseMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final DtoRequestMapper<ClientRequestDto,Client> clientDtoRequestMapper;
    private final DtoResponseMapper<ClientResponseDto,Client> clientDtoResponseMapper;

    public ClientController(ClientService clientService,
                            DtoRequestMapper<ClientRequestDto,Client> clientDtoRequestMapper,
                            DtoResponseMapper<ClientResponseDto, Client> clientDtoResponseMapper) {
        this.clientService = clientService;
        this.clientDtoRequestMapper = clientDtoRequestMapper;
        this.clientDtoResponseMapper = clientDtoResponseMapper;
    }

    @PostMapping
    public ClientResponseDto addClient(@RequestBody ClientRequestDto dto) {
        Client client = clientService.add(clientDtoRequestMapper.fromDto(dto));
        return clientDtoResponseMapper.toDto(client);
    }

    @GetMapping
    public List<ClientResponseDto> getAllClients() {
        return clientService.getAll().stream()
                .map(clientDtoResponseMapper::toDto)
                .collect(Collectors.toList());
    }
}


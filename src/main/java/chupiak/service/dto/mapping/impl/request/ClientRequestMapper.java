package chupiak.service.dto.mapping.impl.request;

import chupiak.model.Client;
import chupiak.model.dto.request.ClientRequestDto;
import chupiak.service.dto.mapping.DtoRequestMapper;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class ClientRequestMapper implements DtoRequestMapper<ClientRequestDto, Client> {
    @Override
    public Client fromDto(ClientRequestDto dto) {
        Client client = new Client();
        client.setCreated(LocalDateTime.parse(dto.getCreated()));
        client.setDeleted(LocalDateTime.parse(dto.getDeleted()));
        return client;
    }
}

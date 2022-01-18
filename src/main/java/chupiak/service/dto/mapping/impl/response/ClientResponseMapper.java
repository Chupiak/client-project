package chupiak.service.dto.mapping.impl.response;

import chupiak.model.Client;
import chupiak.model.dto.response.ClientResponseDto;
import chupiak.service.dto.mapping.DtoResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientResponseMapper implements DtoResponseMapper<ClientResponseDto, Client> {
    @Override
    public ClientResponseDto toDto(Client client) {
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(client.getId());
        clientResponseDto.setCreated(String.valueOf(client.getCreated()));
        return clientResponseDto;
    }
}

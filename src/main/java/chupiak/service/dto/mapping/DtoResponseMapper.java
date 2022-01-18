package chupiak.service.dto.mapping;

public interface DtoResponseMapper<D, C> {
    D toDto(C object);
}

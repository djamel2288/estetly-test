package demo.test.demo_test.mapper;

import demo.test.demo_test.dto.ClientDTO;
import demo.test.demo_test.entity.Client;

import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Override
    ClientDTO toDto(Client client);

    @Override
    @InheritInverseConfiguration
    Client toEntity(ClientDTO clientDTO);

    @Override
    List<ClientDTO> toDto(List<Client> clientList);

    @Override
    List<Client> toEntity(List<ClientDTO> dtoList);

    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }
}
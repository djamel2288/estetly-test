package demo.test.demo_test.service;

import demo.test.demo_test.dto.ClientDTO;
import demo.test.demo_test.entity.Client;
import demo.test.demo_test.service.ClientService;
import demo.test.demo_test.mapper.ClientMapper;
import demo.test.demo_test.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDTO> searchClientsByName(String name, int limit) {
        log.debug("Request to get Client by Name : {}", name);
         List<Client> clients = clientRepository.findByFirstNameContainingOrLastNameContaining(name, name);

        return clients.stream()
                .limit(limit)
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> findAllClients(int limit) {
        log.debug("Request to get all Clients with limit: {}", limit);
        List<Client> clients = clientRepository.findAll();

        return clients.stream()
                .limit(limit)
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }
}
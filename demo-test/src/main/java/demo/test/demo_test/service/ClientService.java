package demo.test.demo_test.service;

import demo.test.demo_test.dto.ClientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Interface for managing {@link demo.test.demo_test.entity.Client}.
 */
@Service
public interface ClientService {
    /*     * Search for the client by name.
     *
     * @param name the name of the client to search for
     * @param limit the maximum number of results to return
     * @return a list of ClientDTO objects matching the search criteria
     */
    List<ClientDTO> searchClientsByName(String name, int limit);

    /**
     * Find all clients with a limit on the number of results.
     *
     * @param limit the maximum number of results to return
     * @return a list of ClientDTO objects
     */
    List<ClientDTO> findAllClients(int limit);
}
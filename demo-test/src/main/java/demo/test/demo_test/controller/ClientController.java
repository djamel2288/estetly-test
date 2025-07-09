package demo.test.demo_test.controller;

import demo.test.demo_test.dto.ClientDTO;
import demo.test.demo_test.service.ClientService;
import demo.test.demo_test.service.ClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final Logger log = LoggerFactory.getLogger(ClientController.class);
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClientDTO>> searchClientsByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "10") int limit) {

        log.debug("Request to get Client by Name : {}", name);

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ClientDTO> clients = clientService.searchClientsByName(name, limit);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ClientDTO>> getClient(
            @RequestParam(defaultValue = "10") int limit) {

        log.debug("Request to get All Client");

        List<ClientDTO> clients = clientService.findAllClients(limit);
        return ResponseEntity.ok(clients);
    }
}
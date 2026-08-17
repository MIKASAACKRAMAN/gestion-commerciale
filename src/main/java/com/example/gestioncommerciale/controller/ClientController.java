package com.example.gestioncommerciale.controller;

import com.example.gestioncommerciale.entity.Client;
import com.example.gestioncommerciale.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping
    public List<Client> getAll() {
        return service.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        return service.getClientById(id).orElse(null);
    }

    @PostMapping
    public Client create(@RequestBody Client client) {
        return service.saveClient(client);
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable Long id,
                         @RequestBody Client client) {
        return service.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteClient(id);
    }
}

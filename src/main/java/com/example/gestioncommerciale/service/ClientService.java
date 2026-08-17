package com.example.gestioncommerciale.service;

import com.example.gestioncommerciale.entity.Client;
import com.example.gestioncommerciale.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public List<Client> getAllClients() {
        return repository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return repository.findById(id);
    }

    public Client saveClient(Client client) {
        return repository.save(client);
    }

    public Client updateClient(Long id, Client client) {

        Client existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client introuvable"));

        existing.setNom(client.getNom());
        existing.setPrenom(client.getPrenom());
        existing.setTelephone(client.getTelephone());
        existing.setEmail(client.getEmail());
        existing.setAdresse(client.getAdresse());

        return repository.save(existing);
    }

    public void deleteClient(Long id) {
        repository.deleteById(id);
    }

}

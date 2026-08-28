package com.example.gestioncommerciale.service;

import com.example.gestioncommerciale.entity.Admin;
import com.example.gestioncommerciale.entity.Client;
import com.example.gestioncommerciale.repository.AdminRepository;
import com.example.gestioncommerciale.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final AdminRepository adminRepository;

    // GET ALL
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    // GET BY ID
    public Client getClientById(Long id) {

        return repository.findById(id)
            .orElseThrow(() ->
                new RuntimeException(
                    "Client introuvable avec l'id : " + id
                )
            );
    }

    // CREATE
    public Client saveClient(Client client) {

        String username = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

        Admin admin = adminRepository.findByUsername(username)
            .orElseThrow(() ->
                new RuntimeException("Admin introuvable")
            );

        client.setAdmin(admin);

        return repository.save(client);
    }

    // UPDATE
    public Client updateClient(Long id, Client client) {

        Client existing = getClientById(id);

        existing.setNom(client.getNom());
        existing.setPrenom(client.getPrenom());
        existing.setTelephone(client.getTelephone());
        existing.setEmail(client.getEmail());
        existing.setAdresse(client.getAdresse());

        return repository.save(existing);
    }

    // DELETE
    public void deleteClient(Long id) {

        Client existing = getClientById(id);

        repository.delete(existing);
    }
}

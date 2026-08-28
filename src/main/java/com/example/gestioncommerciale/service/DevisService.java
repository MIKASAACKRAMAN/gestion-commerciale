package com.example.gestioncommerciale.service;

import com.example.gestioncommerciale.dto.CreateDevisRequest;
import com.example.gestioncommerciale.entity.Admin;
import com.example.gestioncommerciale.entity.Client;
import com.example.gestioncommerciale.entity.Devis;
import com.example.gestioncommerciale.entity.Vehicule;
import com.example.gestioncommerciale.repository.AdminRepository;
import com.example.gestioncommerciale.repository.ClientRepository;
import com.example.gestioncommerciale.repository.DevisRepository;
import com.example.gestioncommerciale.repository.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DevisService {

    private final DevisRepository devisRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final AdminRepository adminRepository;

    // GET ALL
    public List<Devis> getAllDevis() {
        return devisRepository.findAll();
    }

    // GET BY ID
    public Devis getDevisById(Long id) {

        return devisRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Devis introuvable avec l'id : " + id)
            );
    }

    // CREATE
    public Devis createDevis(CreateDevisRequest request) {

        Client client = clientRepository.findById(request.getClientId())
            .orElseThrow(() ->
                new RuntimeException("Client introuvable avec l'id : "
                    + request.getClientId())
            );

        Vehicule vehicule = vehiculeRepository.findById(request.getVehiculeId())
            .orElseThrow(() ->
                new RuntimeException("Véhicule introuvable avec l'id : "
                    + request.getVehiculeId())
            );

        /*
         * Get the currently authenticated admin from the JWT.
         */
        String username = org.springframework.security.core.context.SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

        Admin admin = adminRepository.findByUsername(username)
            .orElseThrow(() ->
                new RuntimeException("Admin introuvable")
            );

        Devis devis = Devis.builder()
            .reference(request.getReference())
            .dateDevis(request.getDateDevis())
            .montant(request.getMontant())
            .statut(request.getStatut())
            .client(client)
            .vehicule(vehicule)
            .admin(admin)
            .build();

        return devisRepository.save(devis);
    }

    // UPDATE
    public Devis updateDevis(Long id, CreateDevisRequest request) {

        Devis existing = getDevisById(id);

        Client client = clientRepository.findById(request.getClientId())
            .orElseThrow(() ->
                new RuntimeException("Client introuvable avec l'id : "
                    + request.getClientId())
            );

        Vehicule vehicule = vehiculeRepository.findById(request.getVehiculeId())
            .orElseThrow(() ->
                new RuntimeException("Véhicule introuvable avec l'id : "
                    + request.getVehiculeId())
            );

        existing.setReference(request.getReference());
        existing.setDateDevis(request.getDateDevis());
        existing.setMontant(request.getMontant());
        existing.setStatut(request.getStatut());
        existing.setClient(client);
        existing.setVehicule(vehicule);

        return devisRepository.save(existing);
    }

    // DELETE
    public void deleteDevis(Long id) {

        Devis existing = getDevisById(id);

        devisRepository.delete(existing);
    }
}

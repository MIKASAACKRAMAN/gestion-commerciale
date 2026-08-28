package com.example.gestioncommerciale.service;

import com.example.gestioncommerciale.entity.Admin;
import com.example.gestioncommerciale.entity.Vehicule;
import com.example.gestioncommerciale.repository.AdminRepository;
import com.example.gestioncommerciale.repository.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeService {

    private final VehiculeRepository repository;
    private final AdminRepository adminRepository;

    // GET ALL
    public List<Vehicule> getAllVehicules() {
        return repository.findAll();
    }

    public Vehicule getVehiculeById(Long id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new RuntimeException(
                    "Véhicule introuvable avec l'id : " + id
                )
            );
    }

    // CREATE
    public Vehicule saveVehicule(Vehicule vehicule) {

        // Get the username from the JWT
        String username = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

        // Find the admin in the database
        Admin admin = adminRepository.findByUsername(username)
            .orElseThrow(() ->
                new RuntimeException("Admin introuvable")
            );

        // Associate the vehicle with the logged-in admin
        vehicule.setAdmin(admin);

        return repository.save(vehicule);
    }

    // UPDATE
    public Vehicule updateVehicule(Long id, Vehicule vehicule) {

        Vehicule existing = getVehiculeById(id);

        existing.setModele(vehicule.getModele());
        existing.setVersion(vehicule.getVersion());
        existing.setEnergie(vehicule.getEnergie());
        existing.setPrix(vehicule.getPrix());
        existing.setStock(vehicule.getStock());

        return repository.save(existing);
    }

    // DELETE
    public void deleteVehicule(Long id) {

        Vehicule existing = getVehiculeById(id);

        repository.delete(existing);
    }
}

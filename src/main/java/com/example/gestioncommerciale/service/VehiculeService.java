package com.example.gestioncommerciale.service;

import com.example.gestioncommerciale.entity.Vehicule;
import com.example.gestioncommerciale.repository.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculeService {

    private final VehiculeRepository repository;

    public List<Vehicule> getAllVehicules() {
        return repository.findAll();
    }

    public Optional<Vehicule> getVehiculeById(Long id) {
        return repository.findById(id);
    }

    public Vehicule saveVehicule(Vehicule vehicule) {
        return repository.save(vehicule);
    }

    public Vehicule updateVehicule(Long id, Vehicule vehicule) {

        Vehicule existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));

        existing.setModele(vehicule.getModele());
        existing.setVersion(vehicule.getVersion());
        existing.setEnergie(vehicule.getEnergie());
        existing.setPrix(vehicule.getPrix());
        existing.setStock(vehicule.getStock());

        return repository.save(existing);
    }

    public void deleteVehicule(Long id) {
        repository.deleteById(id);
    }
}

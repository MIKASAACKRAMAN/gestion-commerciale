package com.example.gestioncommerciale.service;

import com.example.gestioncommerciale.entity.Devis;
import com.example.gestioncommerciale.repository.DevisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevisService {

    private final DevisRepository repository;

    public List<Devis> getAllDevis() {
        return repository.findAll();
    }

    public Optional<Devis> getDevisById(Long id) {
        return repository.findById(id);
    }

    public Devis saveDevis(Devis devis) {
        return repository.save(devis);
    }

    public Devis updateDevis(Long id, Devis devis) {

        Devis existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Devis introuvable"));

        existing.setReference(devis.getReference());
        existing.setDateDevis(devis.getDateDevis());
        existing.setMontant(devis.getMontant());
        existing.setStatut(devis.getStatut());
        existing.setClient(devis.getClient());
        existing.setVehicule(devis.getVehicule());

        return repository.save(existing);
    }

    public void deleteDevis(Long id) {
        repository.deleteById(id);
    }
}

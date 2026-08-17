package com.example.gestioncommerciale.service;

import com.example.gestioncommerciale.entity.Prospect;
import com.example.gestioncommerciale.repository.ProspectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProspectService {

    private final ProspectRepository repository;

    public List<Prospect> getAllProspects() {
        return repository.findAll();
    }

    public Optional<Prospect> getProspectById(Long id) {
        return repository.findById(id);
    }

    public Prospect saveProspect(Prospect prospect) {
        return repository.save(prospect);
    }

    public Prospect updateProspect(Long id, Prospect prospect) {

        Prospect existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Prospect introuvable"));

        existing.setNom(prospect.getNom());
        existing.setPrenom(prospect.getPrenom());
        existing.setTelephone(prospect.getTelephone());
        existing.setEmail(prospect.getEmail());
        existing.setAdresse(prospect.getAdresse());
        existing.setStatut(prospect.getStatut());

        return repository.save(existing);
    }

    public void deleteProspect(Long id) {
        repository.deleteById(id);
    }
}

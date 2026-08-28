package com.example.gestioncommerciale.service;

import com.example.gestioncommerciale.entity.Admin;
import com.example.gestioncommerciale.entity.Prospect;
import com.example.gestioncommerciale.repository.AdminRepository;
import com.example.gestioncommerciale.repository.ProspectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProspectService {

    private final ProspectRepository repository;
    private final AdminRepository adminRepository;

    // GET ALL
    public List<Prospect> getAllProspects() {
        return repository.findAll();
    }

    // GET BY ID
    public Prospect getProspectById(Long id) {

        return repository.findById(id)
            .orElseThrow(() ->
                new RuntimeException(
                    "Prospect introuvable avec l'id : " + id
                )
            );
    }

    // CREATE
    public Prospect saveProspect(Prospect prospect) {

        String username = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

        Admin admin = adminRepository.findByUsername(username)
            .orElseThrow(() ->
                new RuntimeException("Admin introuvable")
            );

        prospect.setAdmin(admin);

        return repository.save(prospect);
    }

    // UPDATE
    public Prospect updateProspect(Long id, Prospect prospect) {

        Prospect existing = getProspectById(id);

        existing.setNom(prospect.getNom());
        existing.setPrenom(prospect.getPrenom());
        existing.setTelephone(prospect.getTelephone());
        existing.setEmail(prospect.getEmail());
        existing.setAdresse(prospect.getAdresse());
        existing.setStatut(prospect.getStatut());
        existing.setDateCreation(prospect.getDateCreation());

        return repository.save(existing);
    }

    // DELETE
    public void deleteProspect(Long id) {

        Prospect existing = getProspectById(id);

        repository.delete(existing);
    }
}

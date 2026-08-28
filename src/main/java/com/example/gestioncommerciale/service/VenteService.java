package com.example.gestioncommerciale.service;

import com.example.gestioncommerciale.dto.CreateVenteRequest;
import com.example.gestioncommerciale.entity.Devis;
import com.example.gestioncommerciale.entity.Vente;
import com.example.gestioncommerciale.enums.DevisStatus;
import com.example.gestioncommerciale.repository.DevisRepository;
import com.example.gestioncommerciale.repository.VenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenteService {

    private final VenteRepository venteRepository;
    private final DevisRepository devisRepository;

    // GET ALL
    public List<Vente> getAllVentes() {
        return venteRepository.findAll();
    }

    // GET BY ID
    public Vente getVenteById(Long id) {

        return venteRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException(
                    "Vente introuvable avec l'id : " + id
                )
            );
    }

    // CREATE
    @Transactional
    public Vente createVente(CreateVenteRequest request) {

        Devis devis = devisRepository.findById(request.getDevisId())
            .orElseThrow(() ->
                new RuntimeException(
                    "Devis introuvable avec l'id : "
                        + request.getDevisId()
                )
            );

        // A sale can only be created from an accepted quotation
        if (devis.getStatut() != DevisStatus.ACCEPTE) {
            throw new RuntimeException(
                "Impossible de créer une vente : le devis doit être accepté."
            );
        }

        // Prevent creating two sales from the same quotation
        if (venteRepository.existsByDevisId(request.getDevisId())) {
            throw new RuntimeException(
                "Une vente existe déjà pour ce devis."
            );
        }

        Vente vente = Vente.builder()
            .dateVente(request.getDateVente())
            .montantFinal(request.getMontantFinal())
            .modePaiement(request.getModePaiement())
            .devis(devis)
            .build();

        return venteRepository.save(vente);
    }

    // UPDATE
    @Transactional
    public Vente updateVente(Long id, CreateVenteRequest request) {

        Vente existing = getVenteById(id);

        Devis devis = devisRepository.findById(request.getDevisId())
            .orElseThrow(() ->
                new RuntimeException(
                    "Devis introuvable avec l'id : "
                        + request.getDevisId()
                )
            );

        existing.setDateVente(request.getDateVente());
        existing.setMontantFinal(request.getMontantFinal());
        existing.setModePaiement(request.getModePaiement());
        existing.setDevis(devis);

        return venteRepository.save(existing);
    }

    // DELETE
    public void deleteVente(Long id) {

        Vente existing = getVenteById(id);

        venteRepository.delete(existing);
    }
}

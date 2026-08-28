package com.example.gestioncommerciale.service;

import com.example.gestioncommerciale.dto.CreateRelanceRequest;
import com.example.gestioncommerciale.entity.Client;
import com.example.gestioncommerciale.entity.Relance;
import com.example.gestioncommerciale.repository.ClientRepository;
import com.example.gestioncommerciale.repository.RelanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelanceService {

    private final RelanceRepository relanceRepository;
    private final ClientRepository clientRepository;

    // GET ALL
    public List<Relance> getAllRelances() {
        return relanceRepository.findAll();
    }

    // GET BY ID
    public Relance getRelanceById(Long id) {

        return relanceRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException(
                    "Relance introuvable avec l'id : " + id
                )
            );
    }

    // CREATE
    public Relance createRelance(CreateRelanceRequest request) {

        Client client = clientRepository.findById(request.getClientId())
            .orElseThrow(() ->
                new RuntimeException(
                    "Client introuvable avec l'id : "
                        + request.getClientId()
                )
            );

        Relance relance = Relance.builder()
            .dateRelance(request.getDateRelance())
            .commentaire(request.getCommentaire())
            .resultat(request.getResultat())
            .client(client)
            .build();

        return relanceRepository.save(relance);
    }

    // UPDATE
    public Relance updateRelance(
        Long id,
        CreateRelanceRequest request) {

        Relance existing = getRelanceById(id);

        Client client = clientRepository.findById(request.getClientId())
            .orElseThrow(() ->
                new RuntimeException(
                    "Client introuvable avec l'id : "
                        + request.getClientId()
                )
            );

        existing.setDateRelance(request.getDateRelance());
        existing.setCommentaire(request.getCommentaire());
        existing.setResultat(request.getResultat());
        existing.setClient(client);

        return relanceRepository.save(existing);
    }

    // DELETE
    public void deleteRelance(Long id) {

        Relance existing = getRelanceById(id);

        relanceRepository.delete(existing);
    }
}

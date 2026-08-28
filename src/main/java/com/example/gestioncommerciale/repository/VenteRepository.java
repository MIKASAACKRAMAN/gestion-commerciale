package com.example.gestioncommerciale.repository;

import com.example.gestioncommerciale.entity.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Vente, Long> {

    boolean existsByDevisId(Long devisId);
}

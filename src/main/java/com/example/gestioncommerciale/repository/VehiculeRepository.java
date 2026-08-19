package com.example.gestioncommerciale.repository;

import com.example.gestioncommerciale.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
}

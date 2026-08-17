package com.example.gestioncommerciale.repository;

import com.example.gestioncommerciale.entity.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProspectRepository extends JpaRepository<Prospect, Long> {
}

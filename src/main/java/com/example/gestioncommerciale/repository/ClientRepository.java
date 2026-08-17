package com.example.gestioncommerciale.repository;

import com.example.gestioncommerciale.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

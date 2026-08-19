package com.example.gestioncommerciale.entity;

import com.example.gestioncommerciale.enums.ProspectStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prospects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prospect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false, unique = true)
    private String email;

    private String adresse;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProspectStatus statut;

    private LocalDate dateCreation;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

}

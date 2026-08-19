package com.example.gestioncommerciale.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

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

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

   @OneToMany(mappedBy = "client")
   @JsonIgnore
   private List<Devis> devis;

}

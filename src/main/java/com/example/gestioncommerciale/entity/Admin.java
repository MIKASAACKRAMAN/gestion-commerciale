package com.example.gestioncommerciale.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Prospect> prospects;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Client> clients;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Vehicule> vehicules;

}

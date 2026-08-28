package com.example.gestioncommerciale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private long totalClients;

    private long totalProspects;

    private long totalVehicules;

    private long totalDevis;

    private long totalVentes;

    private long totalRelances;

    private double totalRevenue;

    private long devisEnAttente;

    private long devisAcceptes;

    private long devisRefuses;

    private long vehiculesEnStock;
}

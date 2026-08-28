package com.example.gestioncommerciale.controller;

import com.example.gestioncommerciale.dto.ConversionRateResponse;
import com.example.gestioncommerciale.dto.DashboardResponse;
import com.example.gestioncommerciale.dto.MonthlySalesResponse;
import com.example.gestioncommerciale.service.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
@Tag(
    name = "Analytics",
    description = "Statistiques et indicateurs de l'activité commerciale"
)
public class AnalyticsController {

    private final AnalyticsService service;

    // =========================================================
    // DASHBOARD
    // =========================================================

    @Operation(
        summary = "Afficher le dashboard",
        description = "Retourne les principales statistiques de l'activité commerciale"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Statistiques récupérées avec succès"
        )
    })
    @GetMapping("/dashboard")
    public DashboardResponse getDashboard() {
        return service.getDashboard();
    }

    // =========================================================
    // SALES BY MONTH
    // =========================================================

    @Operation(
        summary = "Ventes par mois",
        description = "Retourne le nombre de ventes et le chiffre d'affaires pour chaque mois"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Statistiques mensuelles récupérées avec succès"
        )
    })
    @GetMapping("/sales-by-month")
    public List<MonthlySalesResponse> getSalesByMonth() {
        return service.getSalesByMonth();
    }

    // =========================================================
    // CONVERSION RATE
    // =========================================================

    @Operation(
        summary = "Calculer le taux de conversion",
        description = "Calcule le pourcentage de devis acceptés parmi tous les devis"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Taux de conversion calculé avec succès"
        )
    })
    @GetMapping("/conversion-rate")
    public ConversionRateResponse getConversionRate() {
        return service.getConversionRate();
    }
}

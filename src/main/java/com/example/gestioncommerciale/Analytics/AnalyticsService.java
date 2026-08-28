package com.example.gestioncommerciale.service;

import com.example.gestioncommerciale.dto.ConversionRateResponse;
import com.example.gestioncommerciale.dto.DashboardResponse;
import com.example.gestioncommerciale.dto.MonthlySalesResponse;
import com.example.gestioncommerciale.entity.Devis;
import com.example.gestioncommerciale.entity.Vehicule;
import com.example.gestioncommerciale.entity.Vente;
import com.example.gestioncommerciale.repository.ClientRepository;
import com.example.gestioncommerciale.repository.DevisRepository;
import com.example.gestioncommerciale.repository.ProspectRepository;
import com.example.gestioncommerciale.repository.RelanceRepository;
import com.example.gestioncommerciale.repository.VehiculeRepository;
import com.example.gestioncommerciale.repository.VenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final ClientRepository clientRepository;
    private final ProspectRepository prospectRepository;
    private final VehiculeRepository vehiculeRepository;
    private final DevisRepository devisRepository;
    private final VenteRepository venteRepository;
    private final RelanceRepository relanceRepository;

    // =========================================================
    // DASHBOARD
    // =========================================================

    public DashboardResponse getDashboard() {

        List<Devis> devis = devisRepository.findAll();
        List<Vente> ventes = venteRepository.findAll();
        List<Vehicule> vehicules = vehiculeRepository.findAll();

        // -----------------------------------------------------
        // TOTAL REVENUE
        // -----------------------------------------------------

        double totalRevenue = ventes.stream()
            .mapToDouble(vente ->
                vente.getMontantFinal() != null
                    ? vente.getMontantFinal()
                    : 0.0
            )
            .sum();

        // -----------------------------------------------------
        // DEVIS STATUS
        // -----------------------------------------------------

        long devisEnAttente = devis.stream()
            .filter(d ->
                d.getStatut() != null &&
                    d.getStatut().name().equalsIgnoreCase("EN_ATTENTE")
            )
            .count();

        long devisAcceptes = devis.stream()
            .filter(d ->
                d.getStatut() != null &&
                    d.getStatut().name().equalsIgnoreCase("ACCEPTE")
            )
            .count();

        long devisRefuses = devis.stream()
            .filter(d ->
                d.getStatut() != null &&
                    d.getStatut().name().equalsIgnoreCase("REFUSE")
            )
            .count();

        // -----------------------------------------------------
        // TOTAL STOCK
        // -----------------------------------------------------

        long vehiculesEnStock = vehicules.stream()
            .mapToLong(vehicule ->
                vehicule.getStock() != null
                    ? vehicule.getStock()
                    : 0
            )
            .sum();

        // -----------------------------------------------------
        // BUILD DASHBOARD
        // -----------------------------------------------------

        return DashboardResponse.builder()

            .totalClients(clientRepository.count())
            .totalProspects(prospectRepository.count())
            .totalVehicules(vehiculeRepository.count())
            .totalDevis(devisRepository.count())
            .totalVentes(venteRepository.count())
            .totalRelances(relanceRepository.count())

            .totalRevenue(totalRevenue)

            .devisEnAttente(devisEnAttente)
            .devisAcceptes(devisAcceptes)
            .devisRefuses(devisRefuses)

            .vehiculesEnStock(vehiculesEnStock)

            .build();
    }

    // =========================================================
    // SALES BY MONTH
    // =========================================================

    public List<MonthlySalesResponse> getSalesByMonth() {

        List<Vente> ventes = venteRepository.findAll();

        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM");

        Map<String, MonthlySalesResponse> monthlyData =
            new TreeMap<>();

        for (Vente vente : ventes) {

            if (vente.getDateVente() == null) {
                continue;
            }

            String month =
                vente.getDateVente().format(formatter);

            MonthlySalesResponse data =
                monthlyData.getOrDefault(
                    month,
                    new MonthlySalesResponse(
                        month,
                        0,
                        0.0
                    )
                );

            // Number of sales
            data.setSales(data.getSales() + 1);

            // Revenue
            if (vente.getMontantFinal() != null) {

                data.setRevenue(
                    data.getRevenue()
                        + vente.getMontantFinal()
                );
            }

            monthlyData.put(month, data);
        }

        return new ArrayList<>(monthlyData.values());
    }

    // =========================================================
    // CONVERSION RATE
    // =========================================================

    public ConversionRateResponse getConversionRate() {

        List<Devis> devis = devisRepository.findAll();

        long totalDevis = devis.size();

        long devisAcceptes = devis.stream()
            .filter(d ->
                d.getStatut() != null &&
                    d.getStatut().name().equalsIgnoreCase("ACCEPTE")
            )
            .count();

        double conversionRate = totalDevis == 0
            ? 0.0
            : (devisAcceptes * 100.0) / totalDevis;

        return new ConversionRateResponse(
            totalDevis,
            devisAcceptes,
            conversionRate
        );
    }
}

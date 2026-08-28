package com.example.gestioncommerciale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversionRateResponse {

    private long totalDevis;

    private long devisAcceptes;

    private double conversionRate;
}

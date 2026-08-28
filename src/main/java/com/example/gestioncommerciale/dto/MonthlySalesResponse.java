package com.example.gestioncommerciale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlySalesResponse {

    private String month;

    private long sales;

    private double revenue;
}

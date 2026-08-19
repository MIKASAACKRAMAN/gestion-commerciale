package com.example.gestioncommerciale.controller;

import com.example.gestioncommerciale.entity.Vehicule;
import com.example.gestioncommerciale.service.VehiculeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final VehiculeService service;

    @GetMapping
    public List<Vehicule> getAll() {
        return service.getAllVehicules();
    }

    @GetMapping("/{id}")
    public Vehicule getById(@PathVariable Long id) {
        return service.getVehiculeById(id).orElse(null);
    }

    @PostMapping
    public Vehicule create(@RequestBody Vehicule vehicule) {
        return service.saveVehicule(vehicule);
    }

    @PutMapping("/{id}")
    public Vehicule update(@PathVariable Long id,
                           @RequestBody Vehicule vehicule) {
        return service.updateVehicule(id, vehicule);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteVehicule(id);
    }
}

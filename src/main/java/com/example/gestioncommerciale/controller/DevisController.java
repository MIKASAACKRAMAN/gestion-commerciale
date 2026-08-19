package com.example.gestioncommerciale.controller;

import com.example.gestioncommerciale.entity.Devis;
import com.example.gestioncommerciale.service.DevisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devis")
@RequiredArgsConstructor
public class DevisController {

    private final DevisService service;

    @GetMapping
    public List<Devis> getAll() {
        return service.getAllDevis();
    }

    @GetMapping("/{id}")
    public Devis getById(@PathVariable Long id) {
        return service.getDevisById(id).orElse(null);
    }

    @PostMapping
    public Devis create(@RequestBody Devis devis) {
        return service.saveDevis(devis);
    }

    @PutMapping("/{id}")
    public Devis update(@PathVariable Long id,
                        @RequestBody Devis devis) {
        return service.updateDevis(id, devis);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteDevis(id);
    }
}

package com.example.gestioncommerciale.controller;

import com.example.gestioncommerciale.entity.Prospect;
import com.example.gestioncommerciale.service.ProspectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prospects")
@RequiredArgsConstructor
public class ProspectController {

    private final ProspectService service;

    @GetMapping
    public List<Prospect> getAll() {
        return service.getAllProspects();
    }

    @GetMapping("/{id}")
    public Prospect getById(@PathVariable Long id) {
        return service.getProspectById(id).orElse(null);
    }

    @PostMapping
    public Prospect create(@RequestBody Prospect prospect) {
        return service.saveProspect(prospect);
    }

    @PutMapping("/{id}")
    public Prospect update(@PathVariable Long id,
                           @RequestBody Prospect prospect) {
        return service.updateProspect(id, prospect);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteProspect(id);
    }
}

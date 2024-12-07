package org.example.backendwakandaagua.controller;

import org.example.backendwakandaagua.model.plantaTratamientoAgua.PlantaTratamientoDTO;
import org.example.backendwakandaagua.service.PlantaTratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plantas")
public class PlantaTratamientoController {

    @Autowired
    private PlantaTratamientoService plantaTratamientoService;

    @GetMapping
    public List<PlantaTratamientoDTO> getAllPlantas() {
        return plantaTratamientoService.findAll();
    }

    @GetMapping("/{id}")
    public PlantaTratamientoDTO getPlantaById(@PathVariable Long id) {
        return plantaTratamientoService.findById(id);
    }

    @PostMapping
    public PlantaTratamientoDTO createPlanta(@RequestBody PlantaTratamientoDTO dto) {
        return plantaTratamientoService.create(dto);
    }

    @PutMapping("/{id}")
    public PlantaTratamientoDTO updatePlanta(@PathVariable Long id, @RequestBody PlantaTratamientoDTO dto) {
        return plantaTratamientoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePlanta(@PathVariable Long id) {
        plantaTratamientoService.delete(id);
    }
}

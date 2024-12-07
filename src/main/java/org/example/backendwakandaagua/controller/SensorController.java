package org.example.backendwakandaagua.controller;

import org.example.backendwakandaagua.model.sensores.SensorDTO;
import org.example.backendwakandaagua.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensores")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public List<SensorDTO> getAllSensores() {
        return sensorService.findAll();
    }

    @GetMapping("/{id}")
    public SensorDTO getSensorById(@PathVariable Long id) {
        return sensorService.findById(id);
    }

    @PostMapping
    public SensorDTO createSensor(@RequestBody SensorDTO dto) {
        return sensorService.create(dto);
    }

    @PutMapping("/{id}")
    public SensorDTO updateSensor(@PathVariable Long id, @RequestBody SensorDTO dto) {
        return sensorService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteSensor(@PathVariable Long id) {
        sensorService.delete(id);
    }
}

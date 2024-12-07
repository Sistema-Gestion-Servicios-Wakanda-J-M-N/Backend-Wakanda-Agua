package org.example.backendwakandaagua.controller;

import org.example.backendwakandaagua.domain.sensores.LecturaSensor;
import org.example.backendwakandaagua.model.sensores.LecturaSensorDTO;
import org.example.backendwakandaagua.service.LecturaSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecturas")
public class LecturaSensorController {

    @Autowired
    private LecturaSensorService lecturaSensorService;

    @GetMapping
    public List<LecturaSensorDTO> getAllLecturas() {
        return lecturaSensorService.findAll();
    }

    @GetMapping("/{id}")
    public LecturaSensorDTO getLecturaById(@PathVariable Long id) {
        return lecturaSensorService.findById(id);
    }

    @PostMapping
    public LecturaSensorDTO createLectura(@RequestBody LecturaSensorDTO dto) {
        // Convertir DTO a entidad usando un método del servicio
        LecturaSensor lectura = lecturaSensorService.toEntity(dto);

        // Ahora crear la lectura en la base de datos
        return lecturaSensorService.create(lectura);
    }


    @PutMapping("/{id}")
    public LecturaSensorDTO updateLectura(@PathVariable Long id, @RequestBody LecturaSensorDTO dto) {
        return lecturaSensorService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteLectura(@PathVariable Long id) {
        lecturaSensorService.delete(id);
    }
}

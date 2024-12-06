package org.example.backendwakandaagua.service;

import org.example.backendwakandaagua.domain.plantaTratamientoAgua.DatosCalidadAgua;
import org.example.backendwakandaagua.model.plantaTratamientoAgua.DatosCalidadAguaDTO;
import org.example.backendwakandaagua.repos.DatosCalidadAguaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DatosCalidadAguaService {

    @Autowired
    private DatosCalidadAguaRepository datosCalidadAguaRepository;

    private final Random random = new Random();

    // Convertir entidad a DTO
    public DatosCalidadAguaDTO toDTO(DatosCalidadAgua datos) {
        DatosCalidadAguaDTO dto = new DatosCalidadAguaDTO();
        dto.setId(datos.getId());
        dto.setFechaMedicion(datos.getFechaMedicion());
        dto.setNivelPH(datos.getNivelPH());
        dto.setPorcentajePureza(datos.getPorcentajePureza());
        dto.setContaminantesDetectados(datos.getContaminantesDetectados());
        dto.setCaudalActualLitrosPorSegundo(datos.getCaudalActualLitrosPorSegundo());
        dto.setTemperaturaCelsius(datos.getTemperaturaCelsius());
        return dto;
    }

    // Convertir DTO a entidad
    private DatosCalidadAgua toEntity(DatosCalidadAguaDTO dto) {
        DatosCalidadAgua datos = new DatosCalidadAgua();
        datos.setId(dto.getId());
        datos.setFechaMedicion(dto.getFechaMedicion());
        datos.setNivelPH(dto.getNivelPH());
        datos.setPorcentajePureza(dto.getPorcentajePureza());
        datos.setContaminantesDetectados(dto.getContaminantesDetectados());
        datos.setCaudalActualLitrosPorSegundo(dto.getCaudalActualLitrosPorSegundo());
        datos.setTemperaturaCelsius(dto.getTemperaturaCelsius());
        return datos;
    }

    // Obtener todos los datos de calidad
    public List<DatosCalidadAguaDTO> findAll() {
        List<DatosCalidadAgua> datos = datosCalidadAguaRepository.findAll();
        return datos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Obtener datos por ID
    public DatosCalidadAguaDTO findById(Long id) {
        DatosCalidadAgua datos = datosCalidadAguaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Datos de calidad no encontrados"));
        return toDTO(datos);
    }

    // Crear nuevos datos
    public void create(DatosCalidadAguaDTO dto) {
        DatosCalidadAgua datos = toEntity(dto);
        datosCalidadAguaRepository.save(datos);
    }

    // Actualizar datos de calidad del agua
    public void update(Long id, DatosCalidadAguaDTO dto) {
        DatosCalidadAgua datosExistentes = datosCalidadAguaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Datos de calidad del agua no encontrados con ID: " + id));

        // Actualizar solo los campos válidos
        if (dto.getFechaMedicion() != null) {
            datosExistentes.setFechaMedicion(dto.getFechaMedicion());
        }
        if (dto.getNivelPH() != null) {
            datosExistentes.setNivelPH(dto.getNivelPH());
        }
        if (dto.getPorcentajePureza() != null) {
            datosExistentes.setPorcentajePureza(dto.getPorcentajePureza());
        }
        if (dto.getContaminantesDetectados() != null) {
            datosExistentes.setContaminantesDetectados(dto.getContaminantesDetectados());
        }
        if (dto.getCaudalActualLitrosPorSegundo() != null) {
            datosExistentes.setCaudalActualLitrosPorSegundo(dto.getCaudalActualLitrosPorSegundo());
        }
        if (dto.getTemperaturaCelsius() != null) {
            datosExistentes.setTemperaturaCelsius(dto.getTemperaturaCelsius());
        }

        // Guardar cambios
        datosCalidadAguaRepository.save(datosExistentes);
    }

    // Eliminar datos por ID
    public void delete(Long id) {
        datosCalidadAguaRepository.deleteById(id);
    }

    // Generar datos aleatorios para calidad del agua
    public DatosCalidadAgua generarDatosAleatorios() {
        DatosCalidadAgua datos = new DatosCalidadAgua();
        datos.setFechaMedicion(LocalDateTime.now());
        datos.setNivelPH(6.5 + (8.5 - 6.5) * random.nextDouble()); // Valores entre 6.5 y 8.5
        datos.setPorcentajePureza(80.0 + (95.0 - 80.0) * random.nextDouble()); // Valores entre 80% y 95%
        datos.setContaminantesDetectados("Niveles aceptables"); // Texto fijo
        datos.setCaudalActualLitrosPorSegundo(50.0 + (150.0 - 50.0) * random.nextDouble()); // Valores entre 50 y 150
        datos.setTemperaturaCelsius(15.0 + (25.0 - 15.0) * random.nextDouble()); // Valores entre 15 y 25 grados
        return datos;
    }
}


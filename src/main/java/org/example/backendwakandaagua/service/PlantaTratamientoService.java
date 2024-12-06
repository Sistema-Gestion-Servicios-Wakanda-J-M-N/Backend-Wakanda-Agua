package org.example.backendwakandaagua.service;

import org.example.backendwakandaagua.domain.plantaTratamientoAgua.DatosCalidadAgua;
import org.example.backendwakandaagua.domain.plantaTratamientoAgua.PlantaTratamiento;
import org.example.backendwakandaagua.model.plantaTratamientoAgua.DatosCalidadAguaDTO;
import org.example.backendwakandaagua.model.plantaTratamientoAgua.PlantaTratamientoDTO;
import org.example.backendwakandaagua.repos.PlantaTratamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantaTratamientoService {

    @Autowired
    private PlantaTratamientoRepository plantaTratamientoRepository;

    @Autowired
    private DatosCalidadAguaService datosCalidadAguaService;

    // Convertir entidad a DTO
    public PlantaTratamientoDTO toDTO(PlantaTratamiento planta) {
        PlantaTratamientoDTO dto = new PlantaTratamientoDTO();
        dto.setId(planta.getId());
        dto.setNombre(planta.getNombre());
        dto.setUbicacion(planta.getUbicacion());
        dto.setCapacidadMaximaLitros(planta.getCapacidadMaximaLitros());
        dto.setEstadoOperativo(planta.getEstadoOperativo());
        dto.setTipoTratamiento(planta.getTipoTratamiento());
        return dto;
    }

    // Convertir DTO a entidad
    public PlantaTratamiento toEntity(PlantaTratamientoDTO dto) {
        PlantaTratamiento planta = new PlantaTratamiento();
        planta.setId(dto.getId());
        planta.setNombre(dto.getNombre());
        planta.setUbicacion(dto.getUbicacion());
        planta.setCapacidadMaximaLitros(dto.getCapacidadMaximaLitros());
        planta.setEstadoOperativo(dto.getEstadoOperativo());
        planta.setTipoTratamiento(dto.getTipoTratamiento());
        return planta;
    }

    // Obtener todas las plantas
    public List<PlantaTratamientoDTO> findAll() {
        List<PlantaTratamiento> plantas = plantaTratamientoRepository.findAll();
        return plantas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Obtener una planta por ID
    public PlantaTratamientoDTO findById(Long id) {
        PlantaTratamiento planta = plantaTratamientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planta de tratamiento no encontrada con ID: " + id));
        return toDTO(planta);
    }

    // Crear nueva planta
    public PlantaTratamientoDTO create(PlantaTratamientoDTO dto) {
        PlantaTratamiento planta = new PlantaTratamiento();
        planta.setNombre(dto.getNombre());
        planta.setUbicacion(dto.getUbicacion());
        planta.setCapacidadMaximaLitros(dto.getCapacidadMaximaLitros());
        planta.setEstadoOperativo(dto.getEstadoOperativo());
        planta.setTipoTratamiento(dto.getTipoTratamiento());

        // Generar y asignar datos de calidad del agua
        DatosCalidadAgua datos = datosCalidadAguaService.generarDatosAleatorios();
        planta.setDatosCalidadAgua(datos);
        DatosCalidadAguaDTO datosDTO = datosCalidadAguaService.toDTO(datos);
        datosCalidadAguaService.create(datosDTO);
        planta = plantaTratamientoRepository.save(planta);
        return toDTO(planta);
    }

    // Actualizar una planta
    public PlantaTratamientoDTO update(Long id, PlantaTratamientoDTO dto) {
        PlantaTratamiento planta = plantaTratamientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planta de tratamiento no encontrada con ID: " + id));
        planta.setNombre(dto.getNombre());
        planta.setUbicacion(dto.getUbicacion());
        planta.setCapacidadMaximaLitros(dto.getCapacidadMaximaLitros());
        planta.setEstadoOperativo(dto.getEstadoOperativo());
        planta.setTipoTratamiento(dto.getTipoTratamiento());
        DatosCalidadAgua nuevosDatos = datosCalidadAguaService.generarDatosAleatorios();
        planta.setDatosCalidadAgua(nuevosDatos);
        DatosCalidadAguaDTO datosCalidadAguaDTO = datosCalidadAguaService.toDTO(nuevosDatos);
        datosCalidadAguaService.update(nuevosDatos.getId(), datosCalidadAguaDTO);
        plantaTratamientoRepository.save(planta);
        planta = plantaTratamientoRepository.save(planta);
        return toDTO(planta);
    }

    // Eliminar una planta por ID
    public void delete(Long id) {
        if (!plantaTratamientoRepository.existsById(id)) {
            throw new RuntimeException("Planta de tratamiento no encontrada con ID: " + id);
        }
        PlantaTratamiento planta = plantaTratamientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planta de tratamiento no encontrada con ID: " + id));
        plantaTratamientoRepository.delete(planta);
    }
}

package org.example.backendwakandaagua.service;

import jakarta.transaction.Transactional;
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
        if (planta.getDatosCalidadAgua() != null) {
            dto.setDatosCalidadAgua(datosCalidadAguaService.toDTO(planta.getDatosCalidadAgua()));
        }
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
    @Transactional
    public PlantaTratamientoDTO create(PlantaTratamientoDTO dto) {
        PlantaTratamiento planta = new PlantaTratamiento();
        planta.setNombre(dto.getNombre());
        planta.setUbicacion(dto.getUbicacion());
        planta.setCapacidadMaximaLitros(dto.getCapacidadMaximaLitros());
        planta.setEstadoOperativo(dto.getEstadoOperativo());
        planta.setTipoTratamiento(dto.getTipoTratamiento());

        // Generar datos sin asignar ID ni persistirlos ahora
        DatosCalidadAgua datos = datosCalidadAguaService.generarDatosAleatorios();
        // No llamar a datosCalidadAguaService.create(datosDTO) aquí.

        planta.setDatosCalidadAgua(datos);
        // Si cascade = CascadeType.ALL está en la relación, esto persistirá planta y datos juntos
        planta = plantaTratamientoRepository.save(planta);
        return toDTO(planta);
    }

    // Actualizar una planta
    @Transactional
    public PlantaTratamientoDTO update(Long id, PlantaTratamientoDTO dto) {
        PlantaTratamiento planta = plantaTratamientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planta no encontrada con ID: " + id));

        planta.setNombre(dto.getNombre());
        planta.setUbicacion(dto.getUbicacion());
        planta.setCapacidadMaximaLitros(dto.getCapacidadMaximaLitros());
        planta.setEstadoOperativo(dto.getEstadoOperativo());
        planta.setTipoTratamiento(dto.getTipoTratamiento());

        DatosCalidadAgua nuevosDatos = datosCalidadAguaService.generarDatosAleatorios();
        planta.setDatosCalidadAgua(nuevosDatos);

        // Guardar la planta, esto guardará también los nuevos datos gracias al cascade
        planta = plantaTratamientoRepository.save(planta);
        return toDTO(planta);
    }


    // Eliminar una planta por ID
    @Transactional
    public void delete(Long id) {
        if (!plantaTratamientoRepository.existsById(id)) {
            throw new RuntimeException("Planta de tratamiento no encontrada con ID: " + id);
        }
        PlantaTratamiento planta = plantaTratamientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planta de tratamiento no encontrada con ID: " + id));
        plantaTratamientoRepository.delete(planta);
    }
}

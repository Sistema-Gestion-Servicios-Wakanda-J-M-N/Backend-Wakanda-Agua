package org.example.backendwakandaagua.service;

import org.example.backendwakandaagua.domain.sensores.LecturaSensor;
import org.example.backendwakandaagua.model.sensores.LecturaSensorDTO;
import org.example.backendwakandaagua.repos.LecturaSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LecturaSensorService {

    @Autowired
    private LecturaSensorRepository lecturaSensorRepository;

    // Convertir entidad a DTO
    private LecturaSensorDTO toDTO(LecturaSensor lectura) {
        LecturaSensorDTO dto = new LecturaSensorDTO();
        dto.setId(lectura.getId());
        dto.setFechaRegistro(lectura.getFechaRegistro());
        dto.setValorMedido(lectura.getValorMedido());
        dto.setUnidadMedida(lectura.getUnidadMedida());
        dto.setTipoParametro(lectura.getTipoParametro());
        dto.setSensorId(lectura.getSensor().getId());
        return dto;
    }

    // Convertir DTO a entidad
    private LecturaSensor toEntity(LecturaSensorDTO dto) {
        LecturaSensor lectura = new LecturaSensor();
        lectura.setId(dto.getId());
        lectura.setFechaRegistro(dto.getFechaRegistro());
        lectura.setValorMedido(dto.getValorMedido());
        lectura.setUnidadMedida(dto.getUnidadMedida());
        lectura.setTipoParametro(dto.getTipoParametro());
        return lectura;
    }

    // Obtener todas las lecturas
    public List<LecturaSensorDTO> findAll() {
        List<LecturaSensor> lecturas = lecturaSensorRepository.findAll();
        return lecturas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Obtener lecturas por ID
    public LecturaSensorDTO findById(Long id) {
        LecturaSensor lectura = lecturaSensorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lectura de sensor no encontrada con ID: " + id));
        return toDTO(lectura);
    }

    // Crear una nueva lectura
    public LecturaSensorDTO create(LecturaSensor lecturaSensor) {
        LecturaSensor lecturaGuardada = lecturaSensorRepository.save(lecturaSensor);
        return toDTO(lecturaGuardada);
    }

    // Actualizar una lectura existente
    public LecturaSensorDTO update(Long id, LecturaSensorDTO dto) {
        LecturaSensor lecturaExistente = lecturaSensorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lectura de sensor no encontrada con ID: " + id));
        lecturaExistente.setFechaRegistro(dto.getFechaRegistro());
        lecturaExistente.setValorMedido(dto.getValorMedido());
        lecturaExistente.setUnidadMedida(dto.getUnidadMedida());
        lecturaExistente.setTipoParametro(dto.getTipoParametro());
        lecturaSensorRepository.save(lecturaExistente);
        return toDTO(lecturaExistente);
    }

    // Eliminar una lectura por ID
    public void delete(Long id) {
        if (!lecturaSensorRepository.existsById(id)) {
            throw new RuntimeException("Lectura de sensor no encontrada con ID: " + id);
        }
        lecturaSensorRepository.deleteById(id);
    }

    // Obtener todas las lecturas asociadas a un sensor
    public List<LecturaSensorDTO> findBySensorId(Long sensorId) {
        List<LecturaSensor> lecturas = lecturaSensorRepository.findBySensorId(sensorId);
        return lecturas.stream().map(this::toDTO).collect(Collectors.toList());
    }
}

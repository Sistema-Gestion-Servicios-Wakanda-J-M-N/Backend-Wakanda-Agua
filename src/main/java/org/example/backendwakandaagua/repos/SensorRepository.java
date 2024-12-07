package org.example.backendwakandaagua.repos;


import org.example.backendwakandaagua.domain.sensores.Sensor;
import org.example.backendwakandaagua.model.sensores.SensorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    // Encuentra sensores por tipo
    List<Sensor> findByTipoSensor(String tipoSensor);

    // Encuentra sensores por estado
    List<Sensor> findByEstado(String estado);

    List<Sensor> findByPlantaTratamientoId(Long plantaId);

}


package org.example.backendwakandaagua.repos;


import org.example.backendwakandaagua.domain.sensores.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    // Encuentra sensores por tipo
    List<Sensor> findByTipoSensor(String tipoSensor);

    // Encuentra sensores por estado
    List<Sensor> findByEstado(String estado);
}


package org.example.backendwakandaagua.repos;

import org.example.backendwakandaagua.domain.sensores.LecturaSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LecturaSensorRepository extends JpaRepository<LecturaSensor, Long> {
    // Encuentra lecturas de sensores por tipo de parámetro
    List<LecturaSensor> findByTipoParametro(String tipoParametro);

    // Encuentra lecturas de sensores entre dos fechas
    List<LecturaSensor> findByFechaRegistroBetween(LocalDateTime startDate, LocalDateTime endDate);
}

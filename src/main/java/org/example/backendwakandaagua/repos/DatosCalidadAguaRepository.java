package org.example.backendwakandaagua.repos;

import org.example.backendwakandaagua.domain.plantaTratamientoAgua.DatosCalidadAgua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DatosCalidadAguaRepository extends JpaRepository<DatosCalidadAgua, Long> {
    // Encuentra datos de calidad del agua por fecha de medición
    List<DatosCalidadAgua> findByFechaMedicionBetween(LocalDateTime startDate, LocalDateTime endDate);
}
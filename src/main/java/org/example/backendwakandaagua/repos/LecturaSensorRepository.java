package org.example.backendwakandaagua.repos;

import org.example.backendwakandaagua.domain.sensores.LecturaSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LecturaSensorRepository extends JpaRepository<LecturaSensor, Long> {
    List<LecturaSensor> findBySensorId(Long sensorId);
}

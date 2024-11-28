package org.example.backendwakandaagua.repos;


import org.example.backendwakandaagua.domain.plantaTratamientoAgua.PlantaTratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantaTratamientoRepository extends JpaRepository<PlantaTratamiento, Long> {
    // Encuentra una planta de tratamiento por su nombre
    Optional<PlantaTratamiento> findByNombre(String nombre);
}


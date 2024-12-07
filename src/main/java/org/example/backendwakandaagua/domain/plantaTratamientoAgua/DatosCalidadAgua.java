package org.example.backendwakandaagua.domain.plantaTratamientoAgua;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.backendwakandaagua.domain.sensores.Sensor;

import java.time.LocalDateTime;

@Entity
@Table(name = "DatosCalidadAgua")
@Getter
@Setter
public class DatosCalidadAgua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_medicion", nullable = false)
    private LocalDateTime fechaMedicion;

    @Column(name = "nivel_ph", nullable = false)
    private Double nivelPH;

    @Column(name = "porcentaje_pureza", nullable = false)
    private Double porcentajePureza;

    @Column(name = "contaminantes_detectados", nullable = false)
    private String contaminantesDetectados;

    @Column(name = "caudal_actual_litros_por_segundo", nullable = false)
    private Double caudalActualLitrosPorSegundo;

    @Column(name = "temperatura_celsius", nullable = false)
    private Double temperaturaCelsius;

    @OneToOne(mappedBy = "datosCalidadAgua", cascade = CascadeType.ALL, orphanRemoval = true)
    private PlantaTratamiento plantaTratamiento; // Relación con PlantaTratamiento
}

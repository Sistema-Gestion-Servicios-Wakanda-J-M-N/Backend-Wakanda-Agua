package org.example.backendwakandaagua.domain.sensores;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.backendwakandaagua.domain.plantaTratamientoAgua.PlantaTratamiento;

import java.util.List;

@Entity
@Table(name = "Sensores")
@Getter
@Setter
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_sensor", nullable = false)
    private String tipoSensor;

    @Column(name = "ubicacion_exacta", nullable = false)
    private String ubicacionExacta;

    @Column(name = "ultima_fecha_evento", nullable = false)
    private String ultimaFechaEvento;

    @Column(nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "planta_id", nullable = false)
    private PlantaTratamiento plantaTratamiento; // Relación con PlantaTratamiento

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LecturaSensor> lecturas; // Relación con lecturas históricas
}

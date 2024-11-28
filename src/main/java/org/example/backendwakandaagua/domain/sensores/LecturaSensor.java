package org.example.backendwakandaagua.domain.sensores;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "LecturasSensor")
@Getter
@Setter
public class LecturaSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "valor_medido", nullable = false)
    private Double valorMedido;

    @Column(name = "unidad_medida", nullable = false)
    private String unidadMedida;

    @Column(name = "tipo_parametro", nullable = false)
    private String tipoParametro;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;
}


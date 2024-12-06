package org.example.backendwakandaagua.domain.plantaTratamientoAgua;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PlantasTratamiento")
@Getter
@Setter
public class PlantaTratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ubicacion;

    @Column(name = "capacidad_maxima_litros", nullable = false)
    private Double capacidadMaximaLitros;

    @Column(name = "estado_operativo", nullable = false)
    private String estadoOperativo;

    @Column(name = "tipo_tratamiento", nullable = false)
    private String tipoTratamiento;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "datos_calidad_agua_id", nullable = false) // Indica la columna en la base de datos
    private DatosCalidadAgua datosCalidadAgua; // Relación con DatosCalidadAgua
}



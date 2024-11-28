package org.example.backendwakandaagua.model.plantaTratamientoAgua;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DatosCalidadAguaDTO {
    private Long id;
    private LocalDateTime fechaMedicion;
    private Double nivelPH;
    private Double porcentajePureza;
    private String contaminantesDetectados;
    private Double caudalActualLitrosPorSegundo;
    private Double temperaturaCelsius;
}

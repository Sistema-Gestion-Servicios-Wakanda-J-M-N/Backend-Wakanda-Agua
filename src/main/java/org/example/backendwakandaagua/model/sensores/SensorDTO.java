package org.example.backendwakandaagua.model.sensores;

import lombok.Data;

@Data
public class SensorDTO {
    private Long id;
    private String tipoSensor;
    private String ubicacionExacta;
    private String ultimaFechaEvento;
    private String estado;
}

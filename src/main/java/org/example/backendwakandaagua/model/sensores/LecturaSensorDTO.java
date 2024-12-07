package org.example.backendwakandaagua.model.sensores;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LecturaSensorDTO {
    private Long id;
    private LocalDateTime fechaRegistro;
    private Double valorMedido;
    private String unidadMedida;
    private String tipoParametro;
    private Long sensorId;
}



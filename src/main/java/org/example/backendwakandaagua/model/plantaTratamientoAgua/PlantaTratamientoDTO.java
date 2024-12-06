package org.example.backendwakandaagua.model.plantaTratamientoAgua;

import lombok.Data;

@Data
public class PlantaTratamientoDTO {
    private Long id;
    private String nombre;
    private String ubicacion;
    private Double capacidadMaximaLitros;
    private String estadoOperativo;
    private String tipoTratamiento;
    private DatosCalidadAguaDTO datosCalidadAgua;
}


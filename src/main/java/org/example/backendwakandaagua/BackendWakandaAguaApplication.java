package org.example.backendwakandaagua;

import org.example.backendwakandaagua.domain.sensores.LecturaSensor;
import org.example.backendwakandaagua.domain.sensores.Sensor;
import org.example.backendwakandaagua.model.usuario.UsuarioDTO;
import org.example.backendwakandaagua.model.plantaTratamientoAgua.PlantaTratamientoDTO;
import org.example.backendwakandaagua.model.sensores.SensorDTO;
import org.example.backendwakandaagua.model.sensores.LecturaSensorDTO;
import org.example.backendwakandaagua.repos.SensorRepository;
import org.example.backendwakandaagua.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class BackendWakandaAguaApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BackendWakandaAguaApplication.class, args);

        PlantaTratamientoService plantaService = context.getBean(PlantaTratamientoService.class);

        // ========================
        // PLANTA TRATAMIENTO
        // ========================
        System.out.println("\n=== PLANTA TRATAMIENTO ===");
        PlantaTratamientoDTO plantaDTO = new PlantaTratamientoDTO();
        plantaDTO.setNombre("Planta de Tratamiento de Agua A-Wakanda");
        plantaDTO.setUbicacion("Ubicación Norte");
        plantaDTO.setCapacidadMaximaLitros(100000.0);
        plantaDTO.setEstadoOperativo("Operativo");
        plantaDTO.setTipoTratamiento("Osmosis Inversa");

        // CREATE planta
        PlantaTratamientoDTO plantaCreada = plantaService.create(plantaDTO);
        System.out.println("Planta creada: " + plantaCreada);
    }
}

package org.example.backendwakandaagua;

import org.example.backendwakandaagua.domain.sensores.LecturaSensor;
import org.example.backendwakandaagua.domain.sensores.Sensor;
import org.example.backendwakandaagua.domain.usuario.Usuario;
import org.example.backendwakandaagua.model.usuario.UsuarioDTO;
import org.example.backendwakandaagua.model.plantaTratamientoAgua.PlantaTratamientoDTO;
import org.example.backendwakandaagua.model.sensores.SensorDTO;
import org.example.backendwakandaagua.model.sensores.LecturaSensorDTO;
import org.example.backendwakandaagua.model.plantaTratamientoAgua.DatosCalidadAguaDTO;
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

        UsuarioService usuarioService = context.getBean(UsuarioService.class);
        PlantaTratamientoService plantaService = context.getBean(PlantaTratamientoService.class);
        SensorService sensorService = context.getBean(SensorService.class);
        LecturaSensorService lecturaSensorService = context.getBean(LecturaSensorService.class);
        SensorRepository sensorRepository = context.getBean(SensorRepository.class);

        // ========================
        // TEST CRUD USUARIO
        // ========================
        System.out.println("=== TEST CRUD USUARIO ===");
        UsuarioDTO nuevoUsuario = new UsuarioDTO();
        nuevoUsuario.setNombre("Juan");
        nuevoUsuario.setApellido("Perez");
        nuevoUsuario.setEmail("juan.perez@example.com");
        nuevoUsuario.setRoles(List.of("Usuario"));

        // CREATE usuario
        Long usuarioId = usuarioService.create(nuevoUsuario);
        System.out.println("Usuario creado con ID: " + usuarioId);

        // GET usuario
        UsuarioDTO usuarioObtenido = usuarioService.get(usuarioId);
        System.out.println("Usuario obtenido: " + usuarioObtenido);

        // UPDATE usuario
        usuarioObtenido.setNombre("Juan Actualizado");
        usuarioObtenido.setApellido("Perez Actualizado");
        usuarioService.update(usuarioId, usuarioObtenido);
        UsuarioDTO usuarioActualizado = usuarioService.get(usuarioId);
        System.out.println("Usuario luego de update: " + usuarioActualizado);

        // FIND ALL usuarios
        List<UsuarioDTO> listaUsuarios = usuarioService.findAll();
        System.out.println("Lista de usuarios: " + listaUsuarios);

        // DELETE usuario
        usuarioService.delete(usuarioId);
        System.out.println("Usuario con ID " + usuarioId + " eliminado.");

        // ========================
        // TEST CRUD PLANTA TRATAMIENTO
        // ========================
        System.out.println("\n=== TEST CRUD PLANTA TRATAMIENTO ===");
        PlantaTratamientoDTO plantaDTO = new PlantaTratamientoDTO();
        plantaDTO.setNombre("Planta A");
        plantaDTO.setUbicacion("Ubicación A");
        plantaDTO.setCapacidadMaximaLitros(100000.0);
        plantaDTO.setEstadoOperativo("Operativo");
        plantaDTO.setTipoTratamiento("Osmosis Inversa");

        // CREATE planta
        PlantaTratamientoDTO plantaCreada = plantaService.create(plantaDTO);
        System.out.println("Planta creada: " + plantaCreada);

        // GET planta
        PlantaTratamientoDTO plantaObtenida = plantaService.findById(plantaCreada.getId());
        System.out.println("Planta obtenida: " + plantaObtenida);

        // UPDATE planta
        plantaObtenida.setNombre("Planta A Actualizada");
        PlantaTratamientoDTO plantaActualizada = plantaService.update(plantaObtenida.getId(), plantaObtenida);
        System.out.println("Planta actualizada: " + plantaActualizada);

        // FIND ALL plantas
        List<PlantaTratamientoDTO> listaPlantas = plantaService.findAll();
        System.out.println("Lista de plantas: " + listaPlantas);

        // DELETE planta
        plantaService.delete(plantaActualizada.getId());
        System.out.println("Planta con ID " + plantaActualizada.getId() + " eliminada.");

        // ========================
        // TEST CRUD SENSOR (Planta ya creada antes del sensor, pero la borramos arriba)
        // Simplemente cumplir la condición de crear planta antes del sensor
        // Aquí creamos una nueva planta antes del sensor:
        PlantaTratamientoDTO plantaParaSensor = new PlantaTratamientoDTO();
        plantaParaSensor.setNombre("Planta Sensor");
        plantaParaSensor.setUbicacion("Ubicacion Sensor");
        plantaParaSensor.setCapacidadMaximaLitros(200000.0);
        plantaParaSensor.setEstadoOperativo("Operativo");
        plantaParaSensor.setTipoTratamiento("Filtración");
        PlantaTratamientoDTO plantaSensorCreada = plantaService.create(plantaParaSensor);

        System.out.println("\n=== TEST CRUD SENSOR ===");
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setTipoSensor("Temperatura");
        sensorDTO.setUbicacionExacta("Zona Norte");
        sensorDTO.setEstado("Activo");
        sensorDTO.setUltimaFechaEvento(LocalDateTime.now().toString());

        // CREATE sensor
        SensorDTO sensorCreado = sensorService.create(sensorDTO);
        System.out.println("Sensor creado: " + sensorCreado);

        // GET sensor
        SensorDTO sensorObtenido = sensorService.findById(sensorCreado.getId());
        System.out.println("Sensor obtenido: " + sensorObtenido);

        // UPDATE sensor
        sensorObtenido.setTipoSensor("PH");
        sensorObtenido.setUbicacionExacta("Zona Sur");
        SensorDTO sensorActualizado = sensorService.update(sensorObtenido.getId(), sensorObtenido);
        System.out.println("Sensor actualizado: " + sensorActualizado);

        // FIND ALL sensors
        List<SensorDTO> listaSensores = sensorService.findAll();
        System.out.println("Lista de sensores: " + listaSensores);

        // NOTA: No lo borramos todavía porque lo usaremos para LecturaSensor
        // Luego lo eliminaremos al final.

        // ========================
        // TEST CRUD LECTURA SENSOR
        // ========================
        System.out.println("\n=== TEST CRUD LECTURA SENSOR ===");

        // Para crear una LecturaSensor necesitamos el sensor entidad
        Sensor sensorEntidad = sensorRepository.findById(sensorActualizado.getId())
                .orElseThrow(() -> new RuntimeException("Sensor no encontrado para LecturaSensor"));

        LecturaSensor nuevaLectura = new LecturaSensor();
        nuevaLectura.setFechaRegistro(LocalDateTime.now());
        nuevaLectura.setValorMedido(7.0);
        nuevaLectura.setUnidadMedida("pH");
        nuevaLectura.setTipoParametro("Nivel de pH");
        nuevaLectura.setSensor(sensorEntidad);

        // CREATE lectura
        LecturaSensorDTO lecturaCreada = lecturaSensorService.create(nuevaLectura);
        System.out.println("Lectura creada: " + lecturaCreada);

        // GET lectura
        LecturaSensorDTO lecturaObtenida = lecturaSensorService.findById(lecturaCreada.getId());
        System.out.println("Lectura obtenida: " + lecturaObtenida);

        // UPDATE lectura
        lecturaObtenida.setValorMedido(7.5);
        LecturaSensorDTO lecturaActualizada = lecturaSensorService.update(lecturaObtenida.getId(), lecturaObtenida);
        System.out.println("Lectura actualizada: " + lecturaActualizada);

        // FIND ALL lecturas
        List<LecturaSensorDTO> listaLecturas = lecturaSensorService.findAll();
        System.out.println("Lista de lecturas: " + listaLecturas);

        // DELETE lectura
        lecturaSensorService.delete(lecturaActualizada.getId());
        System.out.println("Lectura con ID " + lecturaActualizada.getId() + " eliminada.");

        // Ahora eliminamos el sensor que creamos
        sensorService.delete(sensorActualizado.getId());
        System.out.println("Sensor con ID " + sensorActualizado.getId() + " eliminado.");

        // Finalmente, eliminamos la planta utilizada para el sensor
        plantaService.delete(plantaSensorCreada.getId());
        System.out.println("Planta con ID " + plantaSensorCreada.getId() + " eliminada.");

        System.out.println("\n=== FIN DE LAS PRUEBAS ===");
    }
}


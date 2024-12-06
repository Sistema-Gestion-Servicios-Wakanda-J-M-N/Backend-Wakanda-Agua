package org.example.backendwakandaagua.service;

import org.example.backendwakandaagua.domain.plantaTratamientoAgua.PlantaTratamiento;
import org.example.backendwakandaagua.domain.sensores.Sensor;
import org.example.backendwakandaagua.domain.sensores.LecturaSensor;
import org.example.backendwakandaagua.domain.plantaTratamientoAgua.DatosCalidadAgua;
import org.example.backendwakandaagua.model.plantaTratamientoAgua.PlantaTratamientoDTO;
import org.example.backendwakandaagua.model.sensores.SensorDTO;
import org.example.backendwakandaagua.repos.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private PlantaTratamientoService plantaTratamientoService;

    @Autowired
    private LecturaSensorService lecturaSensorService;

    private final List<Sensor> sensores = new ArrayList<>();

    // Convertir entidad a DTO
    private SensorDTO toDTO(Sensor sensor) {
        SensorDTO dto = new SensorDTO();
        dto.setId(sensor.getId());
        dto.setTipoSensor(sensor.getTipoSensor());
        dto.setUbicacionExacta(sensor.getUbicacionExacta());
        dto.setUltimaFechaEvento(sensor.getUltimaFechaEvento());
        dto.setEstado(sensor.getEstado());
        return dto;
    }

    // Convertir DTO a entidad
    private Sensor toEntity(SensorDTO dto) {
        Sensor sensor = new Sensor();
        sensor.setId(dto.getId());
        sensor.setTipoSensor(dto.getTipoSensor());
        sensor.setUbicacionExacta(dto.getUbicacionExacta());
        sensor.setUltimaFechaEvento(dto.getUltimaFechaEvento());
        sensor.setEstado(dto.getEstado());
        return sensor;
    }

    // CRUD: Obtener todos los sensores
    public List<SensorDTO> findAll() {
        return sensorRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    // CRUD: Obtener sensor por ID
    public SensorDTO findById(Long id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor no encontrado con ID: " + id));
        return toDTO(sensor);
    }

    // CRUD: Crear un nuevo sensor
    public SensorDTO create(SensorDTO dto) {
        Sensor sensor = toEntity(dto);
        sensor.setUltimaFechaEvento(String.valueOf(LocalDateTime.now()));
        return toDTO(sensorRepository.save(sensor));
    }

    // CRUD: Actualizar un sensor
    public SensorDTO update(Long id, SensorDTO dto) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor no encontrado con ID: " + id));
        sensor.setTipoSensor(dto.getTipoSensor());
        sensor.setUbicacionExacta(dto.getUbicacionExacta());
        sensor.setEstado(dto.getEstado());
        sensor.setUltimaFechaEvento(String.valueOf(LocalDateTime.now()));
        return toDTO(sensorRepository.save(sensor));
    }

    // CRUD: Eliminar un sensor
    public void delete(Long id) {
        if (!sensorRepository.existsById(id)) {
            throw new RuntimeException("Sensor no encontrado con ID: " + id);
        }
        sensorRepository.deleteById(id);
    }

    // Scraping programado cada 4 segundos y actualización de sensores
    @Scheduled(fixedRate = 4000)
    public void scrapeDatosYActualizarSensores() {
        List<PlantaTratamientoDTO> plantasDTO = plantaTratamientoService.findAll();
        List<PlantaTratamiento> plantas = plantasDTO.stream().map(plantaTratamientoService::toEntity).toList();

        // Asegurar que hay un sensor disponible para cada planta
        if (sensores.size() < plantas.size()) {
            for (PlantaTratamiento planta : plantas) {
                if (sensores.stream().noneMatch(sensor -> sensor.getUbicacionExacta().equals(planta.getUbicacion()))) {
                    Sensor nuevoSensor = new Sensor();
                    nuevoSensor.setTipoSensor("Calidad de Agua");
                    nuevoSensor.setUbicacionExacta(planta.getUbicacion());
                    nuevoSensor.setEstado("Activo");
                    nuevoSensor.setUltimaFechaEvento(LocalDateTime.now().toString());
                    nuevoSensor.setPlantaTratamiento(planta);
                    sensores.add(sensorRepository.save(nuevoSensor));
                }
            }
        }

        // Scraping de datos y asignación a sensores
        for (int i = 0; i < plantas.size(); i++) {
            PlantaTratamiento planta = plantas.get(i);
            Sensor sensor = sensores.get(i);

            // Obtener los datos de calidad de la planta
            DatosCalidadAgua nuevosDatos = planta.getDatosCalidadAgua();
            sensor.setDatosCalidadAgua(nuevosDatos);

            // Crear una nueva lectura histórica usando el servicio LecturaSensorService
            LecturaSensor nuevaLectura = new LecturaSensor();
            nuevaLectura.setFechaRegistro(LocalDateTime.now());
            nuevaLectura.setValorMedido(nuevosDatos.getNivelPH()); // Ejemplo: pH
            nuevaLectura.setUnidadMedida("pH");
            nuevaLectura.setTipoParametro("Nivel de pH");
            nuevaLectura.setSensor(sensor);

            // Usar el servicio para crear la lectura
            lecturaSensorService.create(nuevaLectura);

            // Actualizar el sensor con los nuevos datos
            sensor.setUltimaFechaEvento(String.valueOf(LocalDateTime.now()));
            sensor.setEstado("Datos Actualizados");
            sensorRepository.save(sensor);

            System.out.println("Sensor " + sensor.getId() + " actualizado con datos de planta: " + planta.getNombre());
        }
    }
}

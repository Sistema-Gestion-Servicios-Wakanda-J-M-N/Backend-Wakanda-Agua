# Backend Wakanda Agua


1. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Frontend-Wakanda
2. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-API-Central   -> API-Central (Gestiona los microservicios y muestra un tablero de Wakanda).
3. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Eureka-Server   -> Eureka-Server/Grafana/Prometheus
4. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/InitManager   -> Lanza el Eureka-Server/Prometheus/Grafana y ejecuta un script para lanzar los microservicios.
5. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Python-Script-Launcher   -> Script para lanzar los microservicios en orden.
6. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend_Wakanda_Salud
7. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Agua
8. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Transporte-Movilidad
9. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Gobierno
10. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Cultura-Ocio-Turismo
11. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Trafico
12. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Seguridad
13. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Residuos
14. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Conectividad-Redes
15. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Energia-Sostenible-Eficiente
16. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Servicios-Emergencia
17. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Educacion

## Participantes del proyecto

- Jaime López Díaz
- Marcos García Benito
- Nicolas Jimenez
- Juan Manuel

## Servicios
Este microservicio basado en el tratamiento de agua en Wakanda, contiene varios servicios responsables de gestionar diferentes aspectos del sistema. A continuación, se presenta un desglose de los servicios:

### UsuarioService
El `UsuarioService` es responsable de gestionar las cuentas de usuario. Proporciona operaciones CRUD para los usuarios y sus credenciales asociadas.
**Métodos:**
- **`findAll()`**: Recupera una lista de todas las cuentas de usuario.
- **`get(Long id)`**: Recupera una cuenta de usuario específica por su ID.
- **`create(UsuarioDTO dto)`**: Crea una nueva cuenta de usuario junto con sus credenciales.
- **`update(Long id, UsuarioDTO dto)`**: Actualiza una cuenta de usuario existente y sus credenciales.
- **`delete(Long id)`**: Elimina una cuenta de usuario y sus credenciales asociadas.
  
### SensorService
El `SensorService` gestiona los sensores utilizados para recopilar datos sobre la calidad del agua. Maneja operaciones CRUD para los sensores, asigna sensores a plantas de tratamiento y proporciona métodos para la recolección y actualización de datos.
**Métodos:**
- **`findAll()`**: Recupera una lista de todos los sensores.
- **`findById(Long id)`**: Recupera un sensor específico por su ID.
- **`create(SensorDTO dto)`**: Crea un nuevo sensor y lo asigna a una planta de tratamiento sin sensores.
- **`findSensoresByPlanta(Long plantaId)`**: Recupera una lista de sensores asociados con una planta de tratamiento específica.
- **`update(Long id, SensorDTO dto)`**: Actualiza un sensor existente.
- **`delete(Long id)`**: Elimina un sensor.
- **`scrapeDatosYActualizarSensores()`**: Recolecta periódicamente datos de las plantas de tratamiento y actualiza los sensores asociados.
  
### PlantaTratamientoService
El `PlantaTratamientoService` gestiona las plantas de tratamiento de agua. Proporciona operaciones CRUD para las plantas, incluida la capacidad de crear nuevas plantas y asignarlas a sensores.
**Métodos:**
- **`findAll()`**: Recupera una lista de todas las plantas de tratamiento.
- **`findById(Long id)`**: Recupera una planta de tratamiento específica por su ID.
- **`create(PlantaTratamientoDTO dto)`**: Crea una nueva planta de tratamiento.
- **`update(Long id, PlantaTratamientoDTO dto)`**: Actualiza una planta de tratamiento existente.
- **`delete(Long id)`**: Elimina una planta de tratamiento.
  
### LecturaSensorService
El `LecturaSensorService` gestiona las lecturas de los sensores. Proporciona operaciones CRUD para las lecturas y métodos para recuperar lecturas asociadas a sensores específicos.
**Métodos:**
- **`findAll()`**: Recupera una lista de todas las lecturas de sensores.
- **`findById(Long id)`**: Recupera una lectura específica de sensor por su ID.
- **`create(LecturaSensor lecturaSensor)`**: Crea una nueva lectura de sensor.
- **`update(Long id, LecturaSensorDTO dto)`**: Actualiza una lectura de sensor existente.
- **`delete(Long id)`**: Elimina una lectura de sensor.
- **`findBySensorId(Long sensorId)`**: Recupera una lista de lecturas asociadas con un sensor específico.
  
### DatosCalidadAguaService
El `DatosCalidadAguaService` maneja los datos de calidad del agua. Proporciona métodos para generar datos aleatorios de calidad del agua y convertir entre entidades de datos y DTOs.
**Métodos:**
- **`toDTO(DatosCalidadAgua datos)`**: Convierte una entidad `DatosCalidadAgua` a un `DatosCalidadAguaDTO`.
- **`toEntity(DatosCalidadAguaDTO dto)`**: Convierte un `DatosCalidadAguaDTO` a una entidad `DatosCalidadAgua`.
- **`generarDatosAleatorios()`**: Genera datos aleatorios de calidad del agua.

## Endpoints REST
El proyecto también incluye endpoints REST para interactuar con los servicios. Endpoints:

### Endpoints de Usuario
- **`/usuarios`**: GET (recuperar todos los usuarios), POST (crear un nuevo usuario)
- **`/usuarios/{id}`**: GET (recuperar un usuario por ID), PUT (actualizar un usuario por ID), DELETE (eliminar un usuario por ID)

## Endpoints REST-Controller:
  
### Endpoints de Sensor
- **`/sensores`**: GET (recuperar todos los sensores), POST (crear un nuevo sensor)
- **`/sensores/{id}`**: GET (recuperar un sensor por ID), PUT (actualizar un sensor por ID), DELETE (eliminar un sensor por ID)
- **`/sensores/planta/{plantaId}`**: GET (recuperar sensores asociados con una planta de tratamiento)
  
### Endpoints de Planta de Tratamiento
- **`/plantas`**: GET (recuperar todas las plantas de tratamiento), POST (crear una nueva planta de tratamiento)
- **`/plantas/{id}`**: GET (recuperar una planta de tratamiento por ID), PUT (actualizar una planta de tratamiento por ID), DELETE (eliminar una planta de tratamiento por ID)
  
### Endpoints de Lectura de Sensor
- **`/lecturas`**: GET (recuperar todas las lecturas de sensores), POST (crear una nueva lectura de sensor)
- **`/lecturas/{id}`**: GET (recuperar una lectura de sensor por ID), PUT (actualizar una lectura de sensor por ID), DELETE (eliminar una lectura de sensor por ID)
- **`/lecturas/sensor/{sensorId}`**: GET (recuperar lecturas asociadas con un sensor específico)

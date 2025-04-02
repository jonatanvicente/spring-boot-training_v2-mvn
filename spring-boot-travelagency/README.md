

### Travel Agency Definitions

Este proyecto es una API REST para una agencia de viajes, desarrollada con Spring Boot. 
* Tipos de cliente / tipo de ticket asignado:
  - Kids (0-12 años) / Bus Ticket
  - Teens (13-18 años) / Motorbike Ticket
  - Adults (19-65 años) / Plane Ticket
  - Seniors (66-100 años) / Train Ticket
* Parámetros de entrada:
```json
{
      "age":20
} 
```
* Parámetros de salida:
```json
{
      "ticketType":"Plane Ticket"
}
```



### Highlights

* Swagger disponible en http://localhost:8764/api-docs/ y http://localhost:8764/swagger-ui-custom.html
* Actuator:
    - http://localhost:8764/actuator/health (debe responder {"status":"UP"})
    - http://localhost:8764/actuator/auditevents
    - http://localhost:8764/actuator/beans
    - http://localhost:8764/actuator/conditions
    - http://localhost:8764/actuator/configprops
    - http://localhost:8764/actuator/env
    - http://localhost:8764/actuator/heapdump (genera volcado de heap para descarga)
    - http://localhost:8764/actuator/httptrace
    - http://localhost:8764/actuator/info
    - http://localhost:8764/actuator/loggers
    - http://localhost:8764/actuator/metrics
    - http://localhost:8764/actuator/mappings
    - http://localhost:8764/actuator/scheduledtasks
    - http://localhost:8764/actuator/threaddump
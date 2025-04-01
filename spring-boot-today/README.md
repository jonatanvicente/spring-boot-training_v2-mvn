

### Highlights

- Reactive Programming (véase https://www.baeldung.com/reactor-core)
- Swagger disponible en http://localhost:8764/api-docs/ y http://localhost:8764/swagger-ui-custom.html
- Actuator:
      - http://localhost:8763/actuator/health (debe responder {"status":"UP"})
      - http://localhost:8763/actuator/auditevents
      - http://localhost:8763/actuator/beans
      - http://localhost:8763/actuator/conditions
      - http://localhost:8763/actuator/configprops
      - http://localhost:8763/actuator/env
      - http://localhost:8763/actuator/heapdump (genera volcado de heap para descarga)
      - http://localhost:8763/actuator/httptrace
      - http://localhost:8763/actuator/info
      - http://localhost:8763/actuator/loggers
      - http://localhost:8763/actuator/metrics
      - http://localhost:8763/actuator/mappings
      - http://localhost:8763/actuator/scheduledtasks
      - http://localhost:8763/actuator/threaddump
- Ejemplos de testing en TodayControllerTest
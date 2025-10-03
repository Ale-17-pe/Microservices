# Usa una imagen de Java 17
FROM openjdk:17-jdk-slim

# Copia el JAR generado (asegúrate de que el nombre coincida)
COPY target/*.jar app.jar

# Expone el puerto del servicio
EXPOSE 8903

# Ejecuta la app
ENTRYPOINT ["java", "-jar", "/app.jar"]
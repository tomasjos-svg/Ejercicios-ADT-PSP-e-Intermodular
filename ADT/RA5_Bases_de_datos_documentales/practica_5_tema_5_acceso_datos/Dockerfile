# Usar una imagen base de OpenJDK (Java 17 JDK)
FROM eclipse-temurin:21-jdk-jammy

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el jar de la aplicación al contenedor
# (Maven debe haber generado el jar en la carpeta target)
COPY target/gestion_biblioteca-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8080 (por documentación, no estrictamente necesario para funcionalidad)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","/app/app.jar"]
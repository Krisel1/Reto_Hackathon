
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/bankingapp-0.0.1-SNAPSHOT.jar  bankingapp.jar

# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 3000

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "bankingapp.jar"]















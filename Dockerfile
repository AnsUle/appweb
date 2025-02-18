# Utiliser une image officielle de JDK pour exécuter l'application
FROM openjdk:21-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR dans l'image Docker
COPY target/AppWeb-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port sur lequel l'application va tourner
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]

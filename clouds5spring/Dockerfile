# Utiliser une seule image qui a à la fois Maven et Java
FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY . .
EXPOSE 8080
# Builder et lancer l'application automatiquement
CMD ["sh", "-c", "mvn clean package -DskipTests && java -jar target/*.war"]
FROM openjdk:12-jdk-alpine
COPY target/prodyna-market-0.0.1-SNAPSHOT.jar prodyna-market.jar
ENTRYPOINT ["java", "-jar", "prodyna-market.jar"]
EXPOSE 8081
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./gradle/wrapper/market-1.0.jar .
ENTRYPOINT ["java", "-jar", "market-1.0.jar"]
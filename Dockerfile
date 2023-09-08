FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./build/libs/market-1.0.jar .
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=pdn", "market-1.0.jar"]
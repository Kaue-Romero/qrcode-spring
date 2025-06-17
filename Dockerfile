FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY
EXPOSE 8080
ENV AWS_S3_REGION=us-east-1
ENV AWS_S3_BUCKET_NAME=qr-code-bucket-kaue
ENTRYPOINT ["java", "-jar", "app.jar"]


FROM eclipse-temurin:20-jdk-alpine

WORKDIR /build
COPY . .
RUN chmod +x mvnw && \
    ./mvnw clean compile package -DskipTests

FROM eclipse-temurin:20-jdk-alpine

ENV SERVER_PORT=8080
EXPOSE 8080

# Copy the application jar from the build container
WORKDIR /app
COPY --from=0 /build/skillmatrix-app/target/skillmatrix-app-*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
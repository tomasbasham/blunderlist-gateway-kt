FROM gradle:5.4.0-jdk8-alpine as builder

WORKDIR /usr/src/app

USER root
RUN apk add --no-cache ca-certificates

# COPY build.gradle.kts .
# COPY settings.gradle.kts .
COPY . .

RUN ./gradlew shadowjar

FROM openjdk:8-jre-alpine

COPY --from=builder /usr/src/app/build/libs/app.jar /gateway.jar
COPY --from=builder /etc/ssl/certs /etc/ssl/certs

ENTRYPOINT ["java", "-jar", "/gateway.jar"]

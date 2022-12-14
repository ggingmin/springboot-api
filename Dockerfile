# jar 파일 빌드
FROM eclipse-temurin:11 as builder
WORKDIR app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootjar

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract



# jar 실행
FROM eclipse-temurin:11

RUN addgroup --system --gid 1000 worker
RUN adduser --system --uid 1000 worker

WORKDIR app
ENV port 8080
COPY --from=builder app/dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/application/ ./

USER worker

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
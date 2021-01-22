FROM openjdk:8-jdk-alpine
LABEL maintener="jl.protois.perso@gmail.com"
EXPOSE 8082
ARG JAR_FILE=target/Abernathy-Patient-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} patient.jar
ENTRYPOINT ["java","-jar","/patient.jar"]
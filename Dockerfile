FROM openjdk:14
LABEL maintener="jl.protois.perso@gmail.com"
EXPOSE 8084
ARG JAR_FILE=target/Abernathy-Patient-0.0.1-SNAPSHOT.jar
#ARG JASYPT_ENCRYPTOR_PASSWORD
#ENV JASYPT_ENCRYPTOR_PASSWORD =${JASYPT_ENCRYPTOR_PASSWORD}
ADD ${JAR_FILE} patient.jar
ENTRYPOINT ["java","-jar","/patient.jar"]
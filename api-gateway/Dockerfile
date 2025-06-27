FROM openjdk:17
COPY ./target /usr/tn/app/tgt/
WORKDIR /usr/tn/app/tgt
#RUN javac Main.java
CMD ["java", "-jar", "api.gateway-0.0.1-SNAPSHOT.jar"]

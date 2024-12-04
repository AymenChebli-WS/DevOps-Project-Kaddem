FROM openjdk:17
EXPOSE 8089
WORKDIR /app
#RUN curl -o 5Arctic4-G3-Kaddem-1.0.jar -L "http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/5Arctic4-G3-Kaddem/1.0/5Arctic4-G3-Kaddem-1.0.jar"
COPY "target/5Arctic4-G3-Kaddem-0.0.1-SNAPSHOT.jar" "5Arctic4-G3-Kaddem-0.0.1-SNAPSHOT.jar"
CMD ["java", "-jar", "5Arctic4-G3-Kaddem-0.0.1-SNAPSHOT.jar"]
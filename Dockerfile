FROM eclipse-temurin:17
VOLUME /tmp
COPY target/*.jar app.jar

ENV DATABASE_URL=jdbc:h2:file:/Users/adamsundberg/Source/GitHub/atSundberg/yrcllrbk/src/main/resources/data/book.h2.mv.db
ENV SPRING_PROFILES_ACTIVE=dev

# Create a directory for the H2 database file
RUN mkdir /data

ENTRYPOINT ["java","-jar","/app.jar"]

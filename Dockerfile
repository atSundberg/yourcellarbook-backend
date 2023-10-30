FROM eclipse-temurin:17

COPY target/*.jar app.jar

VOLUME /data
#ENV DATABASE_URL=jdbc:h2:file:/Users/adamsundberg/Source/GitHub/atSundberg/yrcllrbk/src/main/resources/data/book.h2.mv.db
ENV SPRING_PROFILES_ACTIVE=dev

ENTRYPOINT ["java","-jar","/app.jar"]

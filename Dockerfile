# ---------- build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# copy whole project
COPY . .

# only build controller
RUN mvn -B -DskipTests -pl milk-tea-controller -am clean package

# ---------- run stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# controller jar
COPY --from=build /app/milk-tea-controller/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]

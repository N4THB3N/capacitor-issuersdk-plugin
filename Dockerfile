FROM gradle:7.0-jdk11

WORKDIR /app

# Copiar los archivos de configuración de Gradle desde el directorio src/android
COPY src/android ./src/android

# Copiar los archivos de configuración de Gradle desde el directorio src/android
COPY src/android/build.gradle .
COPY src/android/gradle.properties .

RUN gradle wrapper
RUN chmod +x gradlew

RUN ./gradlew publish --stacktrace --no-watch-fs
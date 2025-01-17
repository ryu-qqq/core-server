# 1. 빌드 단계
FROM openjdk:21-jdk-bullseye AS builder

WORKDIR /app

# 필요한 유틸리티 설치
RUN apt-get update && apt-get install -y findutils

# 프로젝트 복사
COPY . .

# Gradle Wrapper 실행 권한 추가
RUN chmod +x ./gradlew

# 빌드 실행
RUN ./gradlew :core:core-api:bootJar --no-daemon

# 2. 실행 단계
FROM openjdk:21-jdk-slim-bullseye

WORKDIR /app

# 빌드된 JAR 복사
COPY --from=builder /app/core/core-api/build/libs/*.jar app.jar

# 환경 변수 설정
ENV SPRING_PROFILES_ACTIVE=local
ENV SERVER_PORT=8088

ENTRYPOINT ["java", "-jar", "app.jar"]

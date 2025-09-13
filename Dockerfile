# ----- Giai đoạn 1: Build ứng dụng -----
# Sử dụng image Maven để build ra file .jar từ mã nguồn
FROM maven:3.9.7-eclipse-temurin-21 AS build

# Tạo thư mục làm việc bên trong container
WORKDIR /app

# Sao chép file pom.xml và tải các thư viện cần thiết trước
# Điều này giúp tận dụng cache của Docker, lần build sau sẽ nhanh hơn
COPY pom.xml .
RUN mvn dependency:go-offline

# Sao chép toàn bộ mã nguồn và bắt đầu build
COPY src ./src
RUN mvn clean package -DskipTests


# ----- Giai đoạn 2: Chạy ứng dụng -----
# Sử dụng một image Java nhẹ hơn để chạy
FROM openjdk:21-jdk-slim

# Tạo thư mục làm việc
WORKDIR /app

# Chỉ sao chép file .jar đã được build từ giai đoạn trước
COPY --from=build /app/target/*.jar app.jar

# Mở cổng 8080 để bên ngoài có thể truy cập vào ứng dụng Spring Boot
EXPOSE 8080

# Lệnh để khởi chạy ứng dụng khi container bắt đầu
ENTRYPOINT ["java", "-jar", "app.jar"]
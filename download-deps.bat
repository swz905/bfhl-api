@echo off
echo Downloading Spring Boot dependencies...
echo.

REM Create lib directory
if not exist "lib" mkdir lib

REM Download Spring Boot JARs using PowerShell
powershell -Command "& {[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-web/3.2.0/spring-boot-starter-web-3.2.0.jar' -OutFile 'lib\spring-boot-starter-web-3.2.0.jar'}"
powershell -Command "& {[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-validation/3.2.0/spring-boot-starter-validation-3.2.0.jar' -OutFile 'lib\spring-boot-starter-validation-3.2.0.jar'}"

echo Dependencies downloaded successfully!
pause

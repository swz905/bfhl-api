@echo off
echo Compiling BFHL API Server...
javac SimpleBfhlServer.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo.
echo Starting BFHL API Server...
echo API will be available at: http://localhost:8080/bfhl
echo Health check: http://localhost:8080/
echo.
echo Press Ctrl+C to stop the server
echo.
java SimpleBfhlServer
pause

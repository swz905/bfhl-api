@echo off
echo Testing BFHL API...
echo.
echo Waiting for API to start...
timeout /t 10 /nobreak >nul
echo.
echo Testing Example 1:
curl -X POST http://localhost:8080/bfhl -H "Content-Type: application/json" -d "{\"data\": [\"a\",\"1\",\"334\",\"4\",\"R\", \"$\"]}"
echo.
echo.
echo Testing Example 2:
curl -X POST http://localhost:8080/bfhl -H "Content-Type: application/json" -d "{\"data\": [\"2\",\"a\", \"y\", \"4\", \"&\", \"-\", \"*\", \"5\",\"92\",\"b\"]}"
echo.
echo.
echo Testing Example 3:
curl -X POST http://localhost:8080/bfhl -H "Content-Type: application/json" -d "{\"data\": [\"A\",\"ABcD\",\"DOE\"]}"
echo.
echo.
echo Testing Health Check:
curl http://localhost:8080/
echo.
pause

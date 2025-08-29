@echo off
echo Debug Testing BFHL API...
echo.
echo Waiting for API to start...
timeout /t 5 /nobreak >nul
echo.

echo Testing Example 2 with debug info:
echo Input: {"data": ["2","a", "y", "4", "&", "-", "*", "5","92","b"]}
echo.
curl -X POST http://localhost:8080/bfhl -H "Content-Type: application/json" -d "{\"data\": [\"2\",\"a\", \"y\", \"4\", \"&\", \"-\", \"*\", \"5\",\"92\",\"b\"]}"
echo.
echo.

echo Testing Example 2 with simpler format:
echo Input: {"data": ["2","a","y","4","&","-","*","5","92","b"]}
echo.
curl -X POST http://localhost:8080/bfhl -H "Content-Type: application/json" -d "{\"data\": [\"2\",\"a\",\"y\",\"4\",\"&\",\"-\",\"*\",\"5\",\"92\",\"b\"]}"
echo.
echo.

pause

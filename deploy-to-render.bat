@echo off
echo ========================================
echo    BFHL API - Render Deployment Helper
echo ========================================
echo.

echo Step 1: Initialize Git Repository
git init
echo.

echo Step 2: Add all files to Git
git add .
echo.

echo Step 3: Commit changes
git commit -m "Initial commit: BFHL API for Render deployment"
echo.

echo ========================================
echo    NEXT STEPS:
echo ========================================
echo.
echo 1. Create a GitHub repository at github.com
echo 2. Run these commands (replace YOUR_USERNAME):
echo.
echo    git remote add origin https://github.com/YOUR_USERNAME/bfhl-api.git
echo    git push -u origin main
echo.
echo 3. Go to render.com and create a new Web Service
echo 4. Connect your GitHub repository
echo 5. Use Docker environment
echo.
echo See RENDER-DEPLOYMENT.md for detailed instructions!
echo.
pause

# 🚀 Deploy BFHL API to Render

## 📋 Prerequisites

1. **GitHub Account** - You'll need to push your code to GitHub first
2. **Render Account** - Sign up at [render.com](https://render.com)

## 🔧 Step-by-Step Deployment Guide

### Step 1: Create GitHub Repository

1. **Go to GitHub.com** and create a new repository
2. **Name it:** `bfhl-api` or any name you prefer
3. **Make it public** (Render free tier requires public repos)

### Step 2: Push Your Code to GitHub

```bash
# Initialize git repository
git init

# Add all files
git add .

# Commit your changes
git commit -m "Initial commit: BFHL API"

# Add your GitHub repository as remote
git remote add origin https://github.com/YOUR_USERNAME/bfhl-api.git

# Push to GitHub
git push -u origin main
```

### Step 3: Deploy to Render

1. **Go to [render.com](https://render.com)** and sign in
2. **Click "New +"** and select **"Web Service"**
3. **Connect your GitHub repository**
4. **Configure the service:**

   - **Name:** `bfhl-api`
   - **Environment:** `Docker`
   - **Region:** Choose closest to you
   - **Branch:** `main`
   - **Root Directory:** Leave empty (root)
   - **Build Command:** Leave empty (uses Dockerfile)
   - **Start Command:** Leave empty (uses Dockerfile)

5. **Click "Create Web Service"**

### Step 4: Wait for Deployment

- Render will automatically build and deploy your application
- This usually takes 2-5 minutes
- You can monitor the build logs in real-time

### Step 5: Get Your API URL

Once deployment is complete, Render will give you a URL like:
```
https://bfhl-api-xxxxx.onrender.com
```

Your API endpoints will be:
- **Main API:** `https://bfhl-api-xxxxx.onrender.com/bfhl`
- **Health Check:** `https://bfhl-api-xxxxx.onrender.com/`

## 🧪 Test Your Deployed API

### Test with curl:
```bash
# Health check
curl https://bfhl-api-xxxxx.onrender.com/

# Test Example 1
curl -X POST https://bfhl-api-xxxxx.onrender.com/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["a","1","334","4","R", "$"]}'

# Test Example 2
curl -X POST https://bfhl-api-xxxxx.onrender.com/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["2","a", "y", "4", "&", "-", "*", "5","92","b"]}'

# Test Example 3
curl -X POST https://bfhl-api-xxxxx.onrender.com/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["A","ABcD","DOE"]}'
```

### Test with Postman or any API client:
- **URL:** `https://bfhl-api-xxxxx.onrender.com/bfhl`
- **Method:** POST
- **Headers:** `Content-Type: application/json`
- **Body:** 
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

## 📁 Files for Deployment

Your repository should contain these files:
- ✅ `SimpleBfhlServer.java` - Main application
- ✅ `Dockerfile` - Docker configuration
- ✅ `render.yaml` - Render configuration
- ✅ `.dockerignore` - Docker ignore file
- ✅ `build.sh` - Build script

## 🔍 Troubleshooting

### Common Issues:

1. **Build fails:**
   - Check that `SimpleBfhlServer.java` is in the root directory
   - Ensure Java syntax is correct

2. **Service doesn't start:**
   - Check Render logs for errors
   - Verify PORT environment variable handling

3. **API not responding:**
   - Wait 1-2 minutes after deployment
   - Check if the service is running in Render dashboard

### Render Logs:
- Go to your service in Render dashboard
- Click on "Logs" tab
- Check for any error messages

## 🎯 Submit Your Assignment

Once your API is working on Render:

1. **Copy your API URL:** `https://bfhl-api-xxxxx.onrender.com/bfhl`
2. **Test it thoroughly** with all examples
3. **Submit to the form:** https://forms.office.com/r/ZeVpUYp3zV
4. **Include your GitHub repository URL** in the submission

## 💰 Cost

- **Free tier:** $0/month
- **Limitations:** 
  - Service sleeps after 15 minutes of inactivity
  - Takes 30-60 seconds to wake up
  - 750 hours/month free

## 🎉 Success!

Your BFHL API is now live on the internet and ready for submission! 🚀

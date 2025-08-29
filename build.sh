#!/bin/bash
echo "Building BFHL API for Render..."

# Compile the Java application
javac SimpleBfhlServer.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
else
    echo "Compilation failed!"
    exit 1
fi

echo "Build completed successfully!"

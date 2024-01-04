#!/usr/bin/env bash

targetDir="./build/libs"

if [ ! -d "$targetDir" ]; then
    echo "build directory doesn't exist, running gradle build..."
    ./gradlew build
fi

if [ -z "$1" ]; then
    echo "Error: Cron Expression argument is empty. Exiting script."
    exit 1
fi

java -jar $targetDir/cron-expression-parser-1.0.jar "$1"
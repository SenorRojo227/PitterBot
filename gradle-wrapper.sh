#!/bin/bash

if [ ! -f ./gradle/wrapper/gradle-wrapper.jar ]; then
        echo "No Gradle Wrapper found, creating one..."
        gradle wrapper
else
        echo "Gradle Wrapper found, skipping..."
fi
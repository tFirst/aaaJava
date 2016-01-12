#!/bin/bash -x
if [ "$(uname)" == "Darwin" ]; then
echo MacOS
CP="lib/*:outProject/aaa.jar"
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
echo Linux
CP="lib/*:outProject/aaa.jar"
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
echo Windows
CP="h2/*;lib/*;outProject/aaa.jar"
fi

java -cp "$CP" Main "$@"
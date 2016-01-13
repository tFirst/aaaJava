#!/bin/bash -x
if [ "$(uname)" == "Darwin" ]; then
echo MacOS
CP="h2/*:lib/*:target/aaaJava-2.0.jar"
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
echo Linux
CP="h2/*:lib/*:target/aaaJava-2.0.jar"
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
echo Windows
CP="h2/*;lib/*;target/aaaJava-2.0.jar"
fi

java -cp "$CP" Main $@
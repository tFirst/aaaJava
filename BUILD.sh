#!/bin/bash -x

rm -rf outProject
mkdir -p outProject/classes

if [ "$(uname)" == "Darwin" ]; then
    echo MacOS
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
    echo Linux
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
    echo Windows
fi

find . -name "*.java" | xargs javac -cp "lib/*" -d outProject/classes -sourcepath src

mkdir -p outProject/lib
cp lib/* outProject/lib

jar -cfe outProject/aaa.jar Main -C outProject/classes .
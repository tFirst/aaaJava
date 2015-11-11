rm -rf ProjectAAA
mkdir -p ProjectAAA

if [ "$(uname)" == "Darwin" ]; then
    echo MacOS
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
    echo Linux
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
    echo Windows
fi

find . -name "*.java" | xargs javac -cp lib -d ProjectAAA -sourcepath src -verbose

jar -cfe aaaProject Main -C ProjectAAA
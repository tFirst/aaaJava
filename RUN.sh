if [ "$(uname)" == "Darwin" ]; then echo MacOS
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then echo Linux
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then echo Windows
fi

java -cp "lib/*;outProject/aaa.jar" Main $*
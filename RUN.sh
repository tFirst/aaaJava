if [ "$(uname)" == "Darwin" ]; then
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
fi

java -cp "lib" Main $@
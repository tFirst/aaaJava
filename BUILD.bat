mkdir ProjectAAA
javac -classpath lib/commons-cli-1.3.1.jar -sourcepath src -d ProjectAAA src/*.java
jar -cfe aaaProject.jar src.Main -C ProjectAAA .
pause
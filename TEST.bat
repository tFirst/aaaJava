java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main
java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main -h

java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main -login XXX -pass XXX
java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main -login jdoe -pass XXX
java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main -login jdoe -pass sup3rpaZZ

java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main -login jdoe -pass sup3rpaZZ -role READ -res a
java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main -login jdoe -pass sup3rpaZZ -role READ -res a.b
java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main -login jdoe -pass sup3rpaZZ -role XXX -res a.b
java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main -login jdoe -pass sup3rpaZZ -role READ -res XXX
java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main -login jdoe -pass sup3rpaZZ -role WRITE -res a
java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main -login jdoe -pass sup3rpaZZ -role WRITE -res a.bc

java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main -login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol 100
java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main -login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 01-01-2015 -de 2015-12-31 -vol 100
java -classpath "lib/commons-cli-1.3.1.jar;aaaProject.jar" Main -login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol XXX

pause
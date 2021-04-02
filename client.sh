cd request-reply

mvn clean

mvn install

cd target

clear

java -jar request-reply-client-1.0-SNAPSHOT.jar 127.0.0.1 5656 "Um olá de Romulo!"

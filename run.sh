find * -name "*.java" > sources.txt
javac @sources.txt
java -cp src ru.avaj.sjamie.Main "$1"
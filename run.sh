find * -name "*.java" > sources.txt
javac @sources.txt
java -cp src ru.avaj.matruman.Main "$1"
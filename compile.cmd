dir /s /B *.java > sources.txt
javac -d classes -classpath lib\junit.jar @sources.txt
del sources.txt
pause

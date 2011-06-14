@echo off
java -classpath .\*;.\lib\*;.\lib\gdata\*;.\lib\gdata\api\*;.\lib\rome\*;.\lib\skype\*; -Djava.library.path=.\lib\skype org.takewo.main.StartPoint
pause >> nul
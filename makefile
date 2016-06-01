build:
	javac -d bin src/*.java
	jar cfe UPvZ.jar avs.AVS assets/ -C bin .

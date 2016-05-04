build:
	javac -d bin src/*.java
	jar cfe AVS.jar avs.AVS -C bin avs

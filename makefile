build:
	javac -d bin src/*.java
	cd bin && \
	java avs.AVS

# UP vs. Zzztudentz
As if Plants vs. Zombies wasn't crazy enough already.

## Building and running the game
1. Download a copy of the source code and the assets. You can do this by clicking "Clone or download" on the upper right or by going to Releases and picking the version of the code you want.
2. Extract code from the zip archive.
3. Navigate to the project directory/folder using cmd or terminal.

### Ubuntu (might work on Mac)
First time build and run:
``` bash
mkdir bin
make build
```

All subsequent calls to `make build` will build and run the game.

### Windows
1. Create a bin directory in the project folder.
2. To compile
```
javac -d bin src/*.java
```
3. To run
```
java avs.avs
```

## Known issues
1. This game has been known to use up a large amount of resources. It may lag on low spec computers.
2. An OutOfMemoryError is sometimes thrown on computers running Windows. No solution found as of writing.

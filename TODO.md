# 2048 Game Implementation TODO

## Steps to Complete:

1. [x] Create Board.java (Model: 4x4 grid, initialization, move logic for directions, add random tile, game checks).
2. [x] Create Direction.java (Enum for UP, DOWN, LEFT, RIGHT).
3. [x] Create GameView.java (View: JFrame with grid, tile labels, colors, reset button, key listener).
4. [x] Create GameController.java (Controller: Handle inputs, update model and view, check win/lose).
5. [x] Create Main.java (Entry point: Instantiate MVC components and start the game).
6. [x] Compile the project (javac *.java).
7. [x] Test the application (java Main) and verify functionality.
8. [x] Update TODO.md to mark all steps as complete and provide run instructions.

## Run Instructions:
To compile and run the 2048 game:
1. Open a terminal in the parent directory (c:/Users/veere/OneDrive/Desktop/Exponent energy).
2. Run: cd 2048Game && javac *.java
3. Run: cd 2048Game && java Main
The game window will open. Use arrow keys to slide tiles. Press the Reset button to start over. Win by reaching 2048; game over when no moves are possible.

Follow MVC principles throughout. Ensure modular, readable code with proper error handling and tile color mapping.

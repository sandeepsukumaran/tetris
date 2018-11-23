# README #

Sample code for Tetris implemented in Java.

WARNING: This repository contains only a small fraction of the actual code. Non-trivial portions have been obfuscated to preserve academic integrity.

### What is this repository for? ###

* This is a sample of an implementation of the tetris game designed in using the NetBeans IDE.
* Version 1.0

### Who do I talk to? ###

* Repo owner

### Description of files ###
* TetrisGame.java: 
Contains the public class used for the game. This is the starting point of execution. It contains the MainGameFrame class that is used to create the main game window.
* Rotation.java: 
Contains the code used to implement rotation of the tetrominoe on user input.
* Translation.java: 
Contains the code used to implement translation of the tetrominoe on user input.
* DownwardMotion.java: 
Contains code that periodically drops the tetrominoe down a row.
* TetrominoeReplacement.java
Contains code to replace the current tetrominoe with a new one on user input.
* GameSettings.java: 
Contains the class used to display a settings dialog at the start and end of each game.
* DataStructures.java: 
Contains auxilary data structures used in game design.

### How to play? ###
* WARNING: Description below is for code pre-obfuscation.
* Starting a game
When the game is launched, adjust various settings to your liking in the Settings dialog displayed. The scoring factor affects the increment in score when a row of squares is cleared. The number of rows to be cleared to progress to the next level of difficulty can also be set here. The speed factor affects the rate at which the speed of dropping the tetrominoe increases as levels progress. The number of columns in the game area can be set in the text box provided. The number of rows is always twice the number of columns. The minimum number of rows is 8 and maximum is 20. Players can also choose between a normal mode of display and an enlarged mode based on personal preference.
* Understanding the main game window
The main game occurs in a rectangular area to the left. A rectangular window to the top right displays the next tetrominoe that will be introduced to the game once the current one stops moving. The current level of game, number of lines that has been cleared in current level, and score are displayed in the middle right section. Finally, the bottom right area contains the quit button. 
* Controlling the game
Use the left and right mouse button clicks to move the current piece tetrominoe or right by one unit per click. Use the mouse wheel to rotate the tetrominoe - forward scroll causes a clockwise rotation through 90 degrees while a backward scroll causes an anti-clockwise rotation through 90 degrees. Moving the mouse to inside the main play area pauses the game. Moving the mouse to inside the current tetrominoe causes it to be replaced by a new tetrominoe. Note : this tetrominoe will be different from both the original tetrominoe and the tetrominoe displayed in the next block. Also note, a penalty is incurred every time a replacement occurs. To quit the game, press the quit button or close the window.
* End of game
Once there is no more space for a new tetrominoe to enter the game, the game terminates and the game settings dialog is displayed to start the next game. The score can be read from the main game window that continues to be displayed. Pressing the Quit button does not cause the settings dialog to be displayed since it is assumed that the player no longer has interest in playing.

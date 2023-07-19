# Tetris

DISCLAIMER: This project is incomplete and has been archived.

Made by:
- Dino Kupinic
- Samed Karaman
- Max Wöss
- Johannes Rosenauer
- Jannick Angerer

---

### Features:
- 2 Gamemodes
- 8-bit pixel art UI
- Highscore
- Customizable Controls

How to run:
---
Navigate to \out\artifacts\Tetris_jar and double-click Tetris.jar.
You can also open it in an IDE.

### Class Diagram

---

```mermaid
classDiagram
direction BT
class Block {
  + Block(Point, Color) 
  ~ Point blockPoint
  ~ Color blockColor
}
class Controls {
  + Controls(Boolean, String, String, String, String, String, String) 
  - String softdrop
  - String rotate
  - String moveRight
  - Boolean musicCheckbox
  - String moveLeft
  - String fastDrop
  - String hold
}
class Field {
  + Field(int, int, boolean) 
  - Rectangle fieldNode
  - Text debugText
  - boolean containsBlock
  - int FIELD_SIZE
}
class Game {
  + Game(Grid, Gamemode) 
  - Gamemode gamemode
  - Field[][] gameGrid
  ~ boolean collisionCheck
  ~ boolean hasMoved
  - TetrominoFactory tetroFactory
  ~ int yOffset
  ~ int xOffset
  - Tetromino currentTetromino
}
class Gamemode {
<<enumeration>>
  + Gamemode() 
  +  NORMAL
  +  TIMED
}
class Grid {
  + Grid(Pane) 
  - Field[][] grid
  - Pane root
}
class GridSize {
  + GridSize() 
  + int rows
  + int height
  + int width
  + int columns
}
class HelpController {
  + HelpController() 
}
class HighscoreController {
  + HighscoreController() 
  + TextField playerNameInput
  + Button continueButton
}
class ImageLoader {
  + ImageLoader() 
}
class InvalidControlsController {
  + InvalidControlsController() 
  + Button okButton
}
class InvalidShapeException {
  + InvalidShapeException() 
}
class JSONhandler {
  + JSONhandler() 
  - Path PATH_TO_SAVE_JSON
  - Path PATH_TO_CONTROLS_JSON
  - Gson gson
  - File saveJSONfile
  - File controlsJSONfile
}
class Launcher {
  + Launcher() 
}
class MainApplication {
  + MainApplication() 
  ~ Scene scene
  - MainApplication instance
}
class MainController {
  + MainController() 
  + ImageView helpButton
  + ImageView settingsButton
  + ChoiceBox~String~ modeChoiceBox
  + ImageView scoreLabel
  + Label timerHeader
  + Label timerLabel
  + ImageView modeLabel
  + ImageView startButton
  - WindowManager windowManager
  + AnchorPane anchorField
  - Timer timer
  + Label score
  - Pane root
  - Grid grid
  - MainController instance
}
class Player {
  + Player(String, Integer) 
  - Integer highscore
  - String name
}
class Point {
  + Point(int, int) 
  - int x
  - int y
}
class Result {
  - Result(Scene, Stage) 
  - Stage stage
  - Scene scene
}
class Score {
  + Score() 
  - int score
  - int BASE_SCORE_GAIN
}
class SettingsController {
  + SettingsController() 
  + Button restoreButton
  - Controls controlsObject
  + CheckBox musicCheckbox
  + TextField fastDrop
  + TextField hold
  + TextField softDrop
  - SettingsController instance
  + TextField rotate
  + Button saveButton
  + TextField moveRight
  + TextField moveLeft
  + Button cancelButton
}
class Tetromino {
  + Tetromino(ArrayList~Block~, TetrominoShapes) 
  - ArrayList~Block~ TetrominoBlockList
  - TetrominoShapes shape
  ~ int[][] tetroGrid
}
class TetrominoFactory {
  + TetrominoFactory() 
}
class TetrominoRotations {
<<enumeration>>
  + TetrominoRotations() 
  +  LEFT
  +  RIGHT
}
class TetrominoShapes {
<<enumeration>>
  + TetrominoShapes() 
  +  SHAPE_L
  +  SHAPE_O
  +  SHAPE_J
  +  SHAPE_I
  +  SHAPE_Z
  +  SHAPE_S
  +  SHAPE_T
}
class Timer {
  + Timer() 
  - AnimationTimer animationTimer
  - boolean activeTimer
  - long lastFrame
  - float timePlayed
}
class WindowManager {
  + WindowManager() 
  - Stage stage
}

Block "1" *--> "blockPoint 1" Point 
Game "1" *--> "gameGrid *" Field 
Game "1" *--> "gamemode 1" Gamemode 
Game "1" *--> "currentTetromino 1" Tetromino 
Game  ..>  TetrominoFactory : «create»
Game "1" *--> "tetroFactory 1" TetrominoFactory 
Game  ..>  Timer : «create»
Game  ..>  WindowManager : «create»
Grid "1" *--> "grid *" Field 
Grid  ..>  Field : «create»
HighscoreController  ..>  JSONhandler : «create»
JSONhandler  ..>  Player : «create»
MainController  ..>  Game : «create»
MainController  ..>  Grid : «create»
MainController "1" *--> "grid 1" Grid 
MainController "1" *--> "timer 1" Timer 
MainController  ..>  WindowManager : «create»
MainController "1" *--> "windowManager 1" WindowManager 
WindowManager  -->  Result 
SettingsController  ..>  Controls : «create»
SettingsController "1" *--> "controlsObject 1" Controls 
SettingsController  ..>  JSONhandler : «create»
SettingsController  ..>  WindowManager : «create»
Tetromino "1" *--> "TetrominoBlockList *" Block 
Tetromino "1" *--> "shape 1" TetrominoShapes 
TetrominoFactory  ..>  Block : «create»
TetrominoFactory  ..>  InvalidShapeException : «create»
TetrominoFactory  ..>  Point : «create»
TetrominoFactory  ..>  Tetromino : «create»
WindowManager  ..>  Result : «create»
```

### Preview images

![image](preview_images/image1.png)
![image](preview_images/image2.png)
![image](preview_images/image3.png)

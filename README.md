# jetbrains-academy-battleships

A classic game of battleship implemented in Java. The game is played on a 10x10 grid between two players. Each player gets a chance to place their ships and take turns to shoot at the opponent's grid. The player who sinks all of the opponent's ships first is the winner. 

## Key Features
1. **Board:** Creates a 10x10 grid represented by a 2D array of `Cell` objects. Each `Cell` can either be empty or occupied by a `Ship`.

2. **Ships:** A `Ship` is defined with a name and length. Ships can be placed on the board by specifying starting and ending coordinates. The game verifies the placement validity (within board boundaries, correct length, not overlapping with another ship, and not too close to another ship).

3. **Gameplay:** Players take turns shooting at each other's boards. The `shoot` method takes a coordinate, marks it as hit on the board, and checks if a ship is hit or sunk. The game continues until all ships of a player are sunk.

4. **Visual Representation:** The `printShipsPlacement` and `printShotCells` methods provide a visual representation of the game state, showing the locations of ships and the cells that have been shot.

5. **Game:** Manages the overall game flow, including ship placement, turn changes, shooting, and end conditions.

6. **Exception Handling:** The `InvalidPlacementException` is thrown and caught during the game whenever an invalid move or placement is attempted.

Please refer to the source code for more detailed insights. Enjoy the game!

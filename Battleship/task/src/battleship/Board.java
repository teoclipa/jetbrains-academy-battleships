package battleship;

import java.util.List;

public class Board {
    public static final int SIZE = 10;
    private final Cell[][] cells;

    public Board() {
        cells = new Cell[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public static int getSIZE() {
        return SIZE;
    }

    public void placeShip(Ship ship, String startCoord, String endCoord) throws InvalidPlacementException {
        int startRow = startCoord.charAt(0) - 'A';
        int startCol = Integer.parseInt(startCoord.substring(1)) - 1;
        int endRow = endCoord.charAt(0) - 'A';
        int endCol = Integer.parseInt(endCoord.substring(1)) - 1;

        // Swap coordinates if necessary
        if (endRow < startRow || (endRow == startRow && endCol < startCol)) {
            int tempRow = startRow;
            int tempCol = startCol;
            startRow = endRow;
            startCol = endCol;
            endRow = tempRow;
            endCol = tempCol;
        }
        int shipLength = ship.getLength();
        // Check if start and end coordinates are within board boundaries
        if (startRow == endRow && endCol - startCol != shipLength - 1) {
            throw new InvalidPlacementException("Error! Wrong length of the " + ship.getName() + "! Try again:\n");
        }
        if (startRow < 0 || startRow >= SIZE || startCol < 0 || startCol >= SIZE || endRow >= SIZE || endCol < 0 || endCol >= SIZE) {
            throw new InvalidPlacementException("Error! Wrong ship location! Try again:\n");
        }
        // Check if ship can fit within the board limits
        if (endRow - startRow + 1 != shipLength && endCol - startCol + 1 != shipLength) {
            throw new InvalidPlacementException("Error! Wrong ship location! Try again:\n");
        }
        // Check if the ship can fit in the given coordinates
        if (startRow != endRow && startCol != endCol) {
            throw new InvalidPlacementException("Error! Wrong ship location! Try again:\n");
        }

        if (startCol == endCol && endRow - startRow != shipLength - 1) {
            throw new InvalidPlacementException("Error! Wrong length of the Submarine! Try again:\n");
        }
        // Check if the cells for the ship are already occupied
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                if (cells[row][col].isOccupied()) {
                    throw new InvalidPlacementException("Error! Wrong ship location! Try again:\n");
                }
            }
        }
        //Check if the ship is too close to another ship
        int r, c, rMin, rMax, cMin, cMax;
        rMin = Math.max(startRow - 1, 0);
        rMax = Math.min(endRow + 1, SIZE - 1);
        cMin = Math.max(startCol - 1, 0);
        cMax = Math.min(endCol + 1, SIZE - 1);
        for (r = rMin; r <= rMax; r++) {
            for (c = cMin; c <= cMax; c++) {
                if (cells[r][c].isOccupied()) {
                    throw new InvalidPlacementException("Error! You placed it too close to another one. Try again:\n");
                }
            }
        }
        // Place the ship on the board
        int row = startRow;
        int col = startCol;
        for (int i = 0; i < shipLength; i++) {
            Cell cell = cells[row][col];
            cell.setShip(ship);
            ship.addCell(cell);
            if (startRow == endRow) {
                col++;
            } else {
                row++;
            }
        }
    }

    public void shoot(String coord, List<Ship> ships) throws InvalidPlacementException {
        // boolean isShotSuccessful = false;
        int row = coord.charAt(0) - 'A';
        int col = Integer.parseInt(coord.substring(1)) - 1;

//        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
//            throw new InvalidPlacementException("Error! You entered the wrong coordinates! Try again:");
//        }

        Cell cell = cells[row][col];
//        if (cell.isHit()) {
//            throw new InvalidPlacementException("This cell has already been hit, please try again");
//        }
        cell.setHit(true);
        if (cell.isOccupied()) {
            // isShotSuccessful = true;
//            printShotCells();
            int countSunkenShips = 0;
            for (Ship ship : ships) {
                if (ship.isSunk()) {
                    countSunkenShips++;
                }
                if (ship.getCells().contains(cell)) {
                    if (ship.isSunk()) {
                        System.out.println("You sank a ship!");
                    } else {
                        System.out.println("You hit a ship!");
                    }
                }
                if (countSunkenShips == ships.size()) {
                    System.out.println("You sank the last ship. You won. Congratulations!");
                }
            }

            //  printShipsPlacement();

        } else {
            //  printShotCells();
            System.out.println("You missed!");
            // printShipsPlacement();
        }
    }

    public void printShipsPlacement() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int row = 0; row < SIZE; row++) {
            System.out.print((char) ('A' + row) + " ");
            for (int col = 0; col < SIZE; col++) {
                System.out.print(cells[row][col].getStatusString() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printShotCells() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < SIZE; j++) {
                Cell cell = cells[i][j];
                if (cell.isHit()) {
                    if (cell.isOccupied()) {
                        System.out.print("X ");
                    } else {
                        System.out.print("M ");
                    }
                } else {
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
    }
}

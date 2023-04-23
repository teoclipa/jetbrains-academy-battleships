package battleship;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final String name;
    private final int length;
    private final List<Cell> cells;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.cells = new ArrayList<>(length);
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public List<Cell> getCells() {
        return cells;

    }

    public boolean isSunk() {
        for (Cell cell : cells) {
            if (!cell.isHit()) {
                return false;
            }
        }
        return true;
    }
}

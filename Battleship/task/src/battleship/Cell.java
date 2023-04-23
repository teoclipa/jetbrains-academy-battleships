package battleship;

public class Cell {
    private Ship ship;
    private final int row;
    private final int col;
    private boolean isOccupied;
    private boolean isHit;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.isOccupied = false;
        this.isHit = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
        this.isOccupied = true;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public String getStatusString() {
        if (isHit) {
            if (isOccupied) {
                return "X";
            } else {
                return "M";
            }
        } else if (isOccupied) {
            return "O";
        } else {
            return "~";
        }
    }


}

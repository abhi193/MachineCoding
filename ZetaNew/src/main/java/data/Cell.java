package data;

public class Cell {
    private final CellType type;
    private final int value;

    public Cell(CellType type, int value) {
        this.type = type;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

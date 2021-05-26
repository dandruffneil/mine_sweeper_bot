package project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CellEmpty extends Cell {
    private IntegerProperty value;

    public CellEmpty(int x, int y, int value) {
        super(x,y);
        this.value = new SimpleIntegerProperty(value);
    }

    @Override
    public boolean uncover() {
        // if cell flagged or already uncovered, do nothing
        if (getFlagged() || getVisible()) {
            return true;
        }
        setVisible(true);
        return true;
    }

    public IntegerProperty value() {
        return this.value;
    }

    public int getValue() {
        return value().get();
    }

    public void incrementValue() {
        this.value.set(getValue() + 1);
    }

    public void setValue(int newValue) {
        this.value.set(newValue);
    }

    public String toString() {
        return " " + Integer.toString(getValue()) + " ";
    }
}

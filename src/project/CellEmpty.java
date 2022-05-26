package project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CellEmpty extends Cell {
    private IntegerProperty value;

    public CellEmpty(int x, int y, int value) {
        super(x,y);
        this.value = new SimpleIntegerProperty(value);
        updateText();
    }

    @Override
    public void uncover() {
        // if cell flagged or already uncovered, do nothing
        // if (getFlagged() || getVisible()) {
        //     return true;
        // }
        setVisible(true);
    }

    public IntegerProperty value() {
        return this.value;
    }

    public int getValue() {
        return value().get();
    }

    public void incrementValue() {
        this.value.set(getValue() + 1);
        updateText();
    }

    public void setValue(int newValue) {
        this.value.set(newValue);
        updateText();
    }

    public void updateText() {
        if (value().get() == 0) {
            this.setText(" ");
        } else {
            this.setText(Integer.toString(value().get()));
        }
    }

}

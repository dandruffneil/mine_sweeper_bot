package project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CellEmpty extends Cell {
    private IntegerProperty value;

    public CellEmpty(int x, int y, int value) {
        super(x,y,Integer.toString(value));
        this.value = new SimpleIntegerProperty(value);
    }

    @Override
    public boolean uncover() {
        // if cell flagged or already uncovered, do nothing
        // if (getFlagged() || getVisible()) {
        //     return true;
        // }
        setVisible(true);
        for (Item i : getItems()) {
            i.trigger();
            if (i.getDamage() < 0) {
                return false;
            }
        }
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
        this.setText(Integer.toString(getValue()));
    }

    public void setValue(int newValue) {
        this.value.set(newValue);
        this.setText(Integer.toString(newValue));
    }

}

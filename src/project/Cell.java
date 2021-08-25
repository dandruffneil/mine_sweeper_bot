package project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Cell {
    private IntegerProperty x, y;
    private StringProperty text;
    private BooleanProperty visible;
    private BooleanProperty flagged;

    public Cell(int x, int y, String text) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.text = new SimpleStringProperty(text);
        this.visible = new SimpleBooleanProperty(false);
        this.flagged = new SimpleBooleanProperty(false);
    }

    abstract public boolean uncover();
    
    public void toggleFlag() {
        this.flagged.set(!this.flagged.get());
    }

    public BooleanProperty flagged() {
        return this.flagged;
    }

    public boolean getFlagged() {
        return this.flagged.get();
    }

    protected void setVisible(boolean bool) {
        this.visible.set(bool);
    }

    public BooleanProperty visible() {
        return this.visible;
    }

    public boolean getVisible() {
        return this.visible.get();
    }

    public IntegerProperty x() {
        return this.x;
    }

    public IntegerProperty y() {
        return this.y;
    }

    public int getX() {
        return this.x.get();
    }

    public int getY() {
        return this.y.get();
    }

    public StringProperty text() {
        return this.text;
    }

    public String getText() {
        return this.text.get();
    }

}

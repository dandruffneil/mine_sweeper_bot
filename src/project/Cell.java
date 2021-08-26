package project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;
import java.util.List;

public abstract class Cell {
    private IntegerProperty x, y;
    private String text;
    private StringProperty label;
    private BooleanProperty visible;
    private BooleanProperty flagged;
    private List<Item> items;

    public Cell(int x, int y, String text) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.text = text;
        this.label = new SimpleStringProperty(" ");
        this.visible = new SimpleBooleanProperty(false);
        this.flagged = new SimpleBooleanProperty(false);
        this.items = new ArrayList<>();
    }

    abstract public boolean uncover();

    protected List<Item> getItems() {
        return this.items;
    }

    public void addItem(Item item) {
        this.items.add(item);
        this.setText(item.getText());
    }

    public boolean isItemsEmpty() {
        return this.items.isEmpty();
    }

    public void clearItems() {
        this.items.clear();
    }
    
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
        if (bool) {
            this.setLabel(this.getText());
        } else {
            this.setLabel(" ");
        }
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

    public StringProperty label() {
        return this.label;
    }

    public String getLabel() {
        return this.label.get();
    }

    public void setLabel(String label) {
        this.label.set(label);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString() {
        return getLabel();
    }

}

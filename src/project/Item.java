package project;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Item {
    private StringProperty text;
    private int damage;

    public Item(String text, int damage) {
        this.text = new SimpleStringProperty(text);
        this.damage = damage;
    }

    abstract public void trigger();
    
    public StringProperty text() {
        return this.text;
    }

    public String getText() {
        return this.text.get();
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    
}

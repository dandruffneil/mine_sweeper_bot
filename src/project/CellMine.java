package project;

public class CellMine extends Cell{
    
    public CellMine(int x, int y) {
        super(x,y,"x");
    }

    @Override
    public boolean uncover() {
        // if cell flagged or already uncovered, do nothing
        if (getFlagged() || getVisible()) {
            return true;
        }
        setVisible(true);
        return false;
    }
}

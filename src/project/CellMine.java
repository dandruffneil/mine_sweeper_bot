package project;

import java.util.ArrayList;

public class CellMine extends Cell implements MineSubject {

    private ArrayList<MineObserver> mineObservers;
        
    public CellMine(int x, int y) {
        super(x, y);
        this.mineObservers = new ArrayList<MineObserver>();
        this.setText("x");
    }

    @Override
    public void uncover() {
        System.out.println("ouch");
        setVisible(true);
        mineNotify();
    }

    @Override
    public void mineAttach(MineObserver observer) {
        this.mineObservers.add(observer);
    }

    @Override
    public void mineDetach(MineObserver observer) {
        this.mineObservers.remove(observer);
    }

    @Override
    public void mineNotify() {
        for (MineObserver obs : this.mineObservers) {
            obs.mineUpdate();
        }
        
    }
}

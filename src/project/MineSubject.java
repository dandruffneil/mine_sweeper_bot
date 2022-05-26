package project;

public interface MineSubject {
    public void mineAttach(MineObserver observer);
    public void mineDetach(MineObserver observer);
    public void mineNotify();
}

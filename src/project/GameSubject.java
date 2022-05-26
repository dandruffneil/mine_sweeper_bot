package project;

public interface GameSubject {
    public void gameAttach(GameObserver observer);
    public void gameDetach(GameObserver observer);
    public void gameNotify(boolean victory);
}


package project;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public class MineBoard implements MineObserver, GameSubject {

    private Cell[][] board;
    private int nMines;
    private int width;
    private int height;

    private ArrayList<GameObserver> gameObservers;

    public MineBoard(int nMines, int width, int height) {
        // generate the board
        // and set values to cells
        this.nMines = nMines;
        this.width = width;
        this.height = height;
        this.board = new Cell[width][height];
        this.gameObservers = new ArrayList<GameObserver>();
    }

    public boolean generateBoard() {
        if (nMines > height*width) {
            return false;
        }

        // for (int i = 0; i < width; i++) {
        //     for (int j = 0; j < height; j++) {
        //         this.board[i][j] = new CellEmpty(i,j,0);
        //     }
        // }
        // Generate random mines
        // Create hashmap with
        // Key: unique key, Value: coordinates in grid
        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();

        Random rand = new Random();
        while (hm.size() != nMines) {
            int temp = rand.nextInt(width*height);
            int j = 0;
            boolean dupe = false;
            while (hm.get((temp+j) % nMines) != null) {
                if (hm.get((temp+j) % nMines) == temp) {
                    dupe = true;
                    break;
                }
                j++;
            }

            if (!dupe) {
                hm.put((temp+j) % nMines,temp);
            }
        }

        System.out.println(hm);

        // Fill board
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.board[i][j] = new CellEmpty(i,j,0);
            }
        }

        // Place mines
        // Adjust values around mine positions
        for (int i = 0; i < nMines; i++) {
            int tempX = hm.get(i) / width;
            int tempY = hm.get(i) % height;

            for (int j = -1; j <= 1; j++) {
                if (tempY + j >= 0 && tempY + j < height) {
                    for (int k = -1; k <= 1; k++) {
                        if (tempX + k >= 0 && tempX + k < width && !(k == 0 && j == 0)) {
                            CellEmpty cell = (CellEmpty)board[tempX+k][tempY+j];
                            cell.incrementValue();
                        }
                    }

                }
            }
        }

        for (int i = 0; i < nMines; i++) {
            int tempX = hm.get(i) / width;
            int tempY = hm.get(i) % height;

            CellMine mine = new CellMine(tempX, tempY);
            board[tempX][tempY] = mine;
            mine.mineAttach(this);
        }

        // Place mines on the board
        // for (int i = 0; i < nMines; i++) {
        //     int tempX = hm.get(i) / width;
        //     int tempY = hm.get(i) % height;

        //     CellEmpty cell = (CellEmpty)board[tempX][tempY];
        //     Mine mine = new Mine();
        //     cell.addItem(mine);
        // }
        return true;
    }

    public void setMines(int newNMines) {
        this.nMines = newNMines;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int newWidth) {
        this.width = newWidth;
        this.board = new Cell[width][height];
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int newHeight) {
        this.height = newHeight;
        this.board = new Cell[width][height];
    }

    public void displayBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(" " + board[j][i].toString() + " ");
            }
            System.out.println();
        }
    }

    public StringProperty getCellTextProperty(int x, int y) {
        return board[x][y].label();
    }

    public BooleanProperty getCellVisibleProperty(int x, int y) {
        return board[x][y].visible();
    }

    public boolean getVisibleCell(int x, int y) {
        return board[x][y].getVisible();
    }

    // public boolean uncoverCell(int x, int y) {
    //     if (!board[x][y].uncover()) {
    //         System.out.println("early exit");
    //         return false;
    //     }

    //     CellEmpty currCell = (CellEmpty)board[x][y];
        
    //     if (currCell.getValue() == 0) {
    //         for (int i = -1; i <= 1; i++) {
    //             if (y + i >= 0 && y + i < height) {
    //                 for (int j = -1; j <= 1; j++) {
    //                     if (x + j >= 0 && x + j < width) {
    //                         CellEmpty cell = (CellEmpty)board[x+j][y+i];
    //                         // System.out.println("- " + Integer.toString(x+j) + " " + Integer.toString(y+i));
    //                         // System.out.print(cell.getValue());
    //                         // System.out.println(cell.getVisible());
    //                         if (cell.isItemsEmpty() && cell.getVisible() == false) {
    //                             this.uncoverCell(x+j,y+i);
    //                         }
    //                     }
    //                 }
    //             }
    //         }
    //     }
        

    //     return true;
    // }
    public void uncoverCell(int x, int y) {
        ArrayDeque<Cell> queue = new ArrayDeque<Cell>();
        ArrayList<Cell> visited = new ArrayList<Cell>();

        queue.push(board[x][y]);

        while (!queue.isEmpty()) {
            Cell cell = queue.pop();
            int xCoord = cell.getX();
            int yCoord = cell.getY();

            cell.uncover();

            if (cell.getText() == " ") {
                // Uncover cells around it
                for (int i = -1; i <= 1; i++) {
                    if (yCoord + i >= 0 && yCoord + i < height) {
                        for (int j = -1; j <= 1; j++) {
                            if (xCoord + j >= 0 && xCoord + j < width && !(j == 0 && i == 0)) {
                                Cell adjCell = board[xCoord+j][yCoord+i];
                                if (!visited.contains(adjCell) && !adjCell.getVisible()) {
                                    queue.add(board[xCoord+j][yCoord+i]);
                                }
                            }
                        }
                    }
                }
            }

            visited.add(cell);
        }
    }

    public void flagCell(int x, int y) {
        board[x][y].toggleFlag();
    }

    public boolean getFlaggedCell(int x, int y) {
        return board[x][y].getFlagged();
    }

    public void checkCleared() {
        int flagCount = 0;
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (getFlaggedCell(i, j)) {
                    flagCount++;
                } else if (!getVisibleCell(i, j)) {
                    flagCount = -1;
                    break;
                }
            }
        }

        if (flagCount == nMines) {
            this.gameNotify(true);
        }
    }

    @Override
    public void gameAttach(GameObserver observer) {
        this.gameObservers.add(observer);
    }

    @Override
    public void gameDetach(GameObserver observer) {
        this.gameObservers.remove(observer);
    }

    @Override
    public void gameNotify(boolean victory) {
        for (GameObserver obs : this.gameObservers) {
            obs.gameUpdate(victory);
        }
    }

    @Override
    public void mineUpdate() {
        this.gameNotify(false);
    }
    
    public static void main(String[] args) {
        MineBoard board = new MineBoard(20,10,10);
        System.out.println(board.generateBoard());
        board.displayBoard();
    }
}


package project;

import java.util.HashMap;
import java.util.Random;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public class MineBoard {

    private Cell[][] board;
    private int nMines;
    private int width;
    private int height;

    public MineBoard(int nMines, int width, int height) {
        // generate the board
        // and set values to cells
        this.nMines = nMines;
        this.width = width;
        this.height = height;
        this.board = new Cell[width][height];
    }

    public boolean generateBoard() {
        if (nMines > height*width) {
            return false;
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.board[i][j] = new CellEmpty(i,j,0);
            }
        }
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

        // Adjust values around mine positions
        for (int i = 0; i < nMines; i++) {
            int tempX = hm.get(i) / width;
            int tempY = hm.get(i) % height;

            for (int j = -1; j <= 1; j++) {
                if (tempY + j >= 0 && tempY + j < height) {
                    for (int k = -1; k <= 1; k++) {
                        if (tempX + k >= 0 && tempX + k < width) {
                            CellEmpty cell = (CellEmpty)board[tempX+k][tempY+j];
                            cell.incrementValue();
                        }
                    }

                }
            }
        }

        // Place mines on the board
        for (int i = 0; i < nMines; i++) {
            int tempX = hm.get(i) / width;
            int tempY = hm.get(i) % height;

            board[tempX][tempY] = new CellMine(tempX,tempY);
        }
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
                System.out.print(board[j][i]);
            }
            System.out.println();
        }
    }

    public StringProperty getCellTextProperty(int x, int y) {
        return board[x][y].text();
    }

    public BooleanProperty getCellVisibleProperty(int x, int y) {
        return board[x][y].visible();
    }

    public static void main(String[] args) {
        MineBoard board = new MineBoard(20,10,10);
        System.out.println(board.generateBoard());
        board.displayBoard();
    }
    

}

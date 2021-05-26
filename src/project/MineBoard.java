
package project;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.board[i][j] = new CellEmpty(i,j,0);
            }
        }
    }

    public boolean generateBoard() {
        if (nMines > height*width) {
            return false;
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

        // Add mines to board and adjust values

        return true;
    }

    public void setMines(int newNMines) {
        this.nMines = newNMines;
    }

    public static void main(String[] args) {
        MineBoard board = new MineBoard(10,3,3);
        board.generateBoard();
    }
    

}

/**
 *
 */
package project;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Conway's Game of Life on a 10x10 grid.
 *
 * @author Robert Clifton-Everest
 *
 */
public class MineBoard {

    private BooleanProperty[][] board;

    public MineBoard() {
        this.board = new BooleanProperty[10][10];
        
    }

    // public void tick() {
    //     BooleanProperty oldBoard[][] = new BooleanProperty[10][10];
    //     for (int i = 0; i < 10; i++) {
    //         for (int j = 0; j < 10; j++) {
    //             oldBoard[i][j] = new SimpleBooleanProperty(isAlive(i, j));
    //         }
    //     }

    //     for (int i = 0; i < 10; i++) {
    //         for (int j = 0; j < 10; j++) {
    //             int neighbours = countNeighbours(oldBoard, i, j);
    //             if (neighbours < 2 || neighbours > 3) {
    //                 ensureDead(i, j);
    //             } else if (neighbours == 3) {
    //                 ensureAlive(i, j);
    //             }
    //         }
    //     }
    // }

    public BooleanProperty cellProperty(int x, int y) {
        return this.board[x][y];
    }

}

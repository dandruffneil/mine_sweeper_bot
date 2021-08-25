package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;


/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class MineSweeperController {

    private MineBoard board;
    private Timeline time;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button resetButton;

    @FXML
    private Button playButton;

    @FXML
    private TextField nMinesText;

    @FXML
    private TextField widthText;

    @FXML
    private TextField heightText;
    

    public MineSweeperController(int nRows, int nCols, int nMines) {
        this.board = new MineBoard(nRows,nCols,nMines);

        // this.time = new Timeline();
        // time.setCycleCount(Animation.INDEFINITE);
        // time.getKeyFrames().add(new KeyFrame(Duration.millis(500),
        //     new EventHandler<ActionEvent>() {
        //         @Override public void handle(ActionEvent event) {
        //             game.tick();
        //         }
        //     }));

    }

    @FXML
    public void handleResetButton(ActionEvent event) {
        
    }

    @FXML
    public void handlePlayButton(ActionEvent event) {
        
    }

    // @FXML
    // public void handlePlayButton(ActionEvent event) {
    //     if (playButton.getText().equals("Play")) {
    //         time.play();
    //         playButton.setText("Stop");
    //     } else if (playButton.getText().equals("Stop")) {
    //         time.stop();
    //         playButton.setText("Play");
    //     }
    // }

    @FXML
    public void initialize(){
        board.generateBoard();
        for (int rowIndex = 0; rowIndex < board.getHeight(); rowIndex++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.ALWAYS);
            rc.setFillHeight(true);
            gridPane.getRowConstraints().add(rc);
        }

        for (int colIndex = 0; colIndex < board.getHeight(); colIndex++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.ALWAYS);
            cc.setFillWidth(true);
            gridPane.getColumnConstraints().add(cc);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button button = new Button();
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                board.getCellTextProperty(i, j).bindBidirectional(button.textProperty());
                board.getCellVisibleProperty(i, j).bindBidirectional(button.visibleProperty());
                gridPane.add(button, i, j);
            }
        }
    }

}


package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

    // @FXML
    // private Button playButton;

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
        initialize();
    }

    // @FXML
    // public void handlePlayButton(ActionEvent event) {
        
    // }

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

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ToggleButton button = new ToggleButton();
                button.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 10));
                button.setMinSize(25, 25);
                button.textProperty().bindBidirectional(board.getCellTextProperty(i, j));
                button.selectedProperty().bindBidirectional(board.getCellVisibleProperty(i, j));
                button.setStyle("-fx-background-color: #f0f0f0;-fx-border-color: #c8c8c8");
                

                final int finalI = i;
                final int finalJ = j;
                button.setOnMouseClicked(event ->
                {
                    if (event.getButton() == MouseButton.PRIMARY && !board.getFlaggedCell(finalI, finalJ)) {
                        board.uncoverCell(finalI, finalJ);
                        button.setStyle("-fx-background-color: #d6d6d6;-fx-border-color: #c8c8c8");
                    } else if (event.getButton() == MouseButton.SECONDARY && !board.getVisibleCell(finalI, finalJ)) {
                        board.flagCell(finalI, finalJ);
                        if (board.getFlaggedCell(finalI, finalJ)) {
                            button.setStyle("-fx-background-color: #ff9e9e;-fx-border-color: #c8c8c8");
                        } else {
                            button.setStyle("-fx-background-color: #f0f0f0;-fx-border-color: #c8c8c8");
                        }
                    }
                });
                gridPane.add(button, i, j);
            }
        }
    }

}


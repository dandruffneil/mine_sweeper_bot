package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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
public class MineSweeperController implements GameObserver {

    private MineBoard board;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button resetButton;

    @FXML
    private Label victoryLabel, defeatLabel;

    @FXML
    private TextField nMinesText;

    @FXML
    private TextField widthText;

    @FXML
    private TextField heightText;
    

    public MineSweeperController() {
        this.board = new MineBoard(10,10,10);
        this.board.gameAttach(this);

    }

    @FXML
    public void handleResetButton(ActionEvent event) {
        int height = Integer.parseInt(heightText.getText());
        int width = Integer.parseInt(widthText.getText());
        int nMines = Integer.parseInt(nMinesText.getText());

        if (width < 1) {
            widthText.setStyle("-fx-border-color: #ff0000");
        } else if (height < 1) {
            heightText.setStyle("-fx-border-color: #ff0000");
        } else if (nMines > height * width) {
            nMinesText.setStyle("-fx-border-color: #ff0000");
        } else {
            this.board = new MineBoard(nMines, width, height);
            this.board.gameAttach(this);
            widthText.setStyle("");
            heightText.setStyle("");
            nMinesText.setStyle("");
            victoryLabel.setVisible(false);
            defeatLabel.setVisible(false);
            initialize();
        }
    }

    @FXML
    public void initialize(){
        gridPane.getChildren().clear();
        board.generateBoard();

        for (int i = 0; i < this.board.getWidth(); i++) {
            for (int j = 0; j < this.board.getHeight(); j++) {
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
                        updateCellColours();
                    } else if (event.getButton() == MouseButton.SECONDARY && !board.getVisibleCell(finalI, finalJ)) {
                        board.flagCell(finalI, finalJ);
                        updateCellColours();
                        // if (board.getFlaggedCell(finalI, finalJ)) {
                        //     button.setStyle("-fx-background-color: #ff9e9e;-fx-border-color: #c8c8c8");
                        // } else {
                        //     button.setStyle("-fx-background-color: #f0f0f0;-fx-border-color: #c8c8c8");
                        // }
                    }
                    this.board.checkCleared();
                });
                gridPane.add(button, i, j);
            }
        }
    }

    public void updateCellColours() {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                int index = i*board.getWidth() + j;
                if (board.getVisibleCell(i, j)) {
                    gridPane.getChildren().get(index).setStyle("-fx-background-color: #d6d6d6;-fx-border-color: #c8c8c8");
                } else if (board.getFlaggedCell(i, j)) {
                    gridPane.getChildren().get(index).setStyle("-fx-background-color: #ff9e9e;-fx-border-color: #c8c8c8");
                } else if (!board.getFlaggedCell(i, j)) {
                    gridPane.getChildren().get(index).setStyle("-fx-background-color: #f0f0f0;-fx-border-color: #c8c8c8");
                }
            }
        }
    }

    @Override
    public void gameUpdate(boolean victory) {
        if (victory) {
            victoryLabel.setVisible(true);
        } else {
            defeatLabel.setVisible(true);
        }
        
    }

}


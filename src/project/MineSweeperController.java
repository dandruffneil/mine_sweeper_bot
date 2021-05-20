package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
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

    private MineBoard game;
    private Timeline time;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button tickButton;

    @FXML
    private Button playButton;

    public MineSweeperController() {
        // this.game = new GameOfLife();
        // this.time = new Timeline();
        // time.setCycleCount(Animation.INDEFINITE);
        // time.getKeyFrames().add(new KeyFrame(Duration.millis(500),
        //     new EventHandler<ActionEvent>() {
        //         @Override public void handle(ActionEvent event) {
        //             game.tick();
        //         }
        //     }));

    }

    // @FXML
    // public void handleTickButton(ActionEvent event) {
    //     game.tick();
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

    // @FXML
    // public void initialize(){
    //     for (int i = 0; i < 10; i++) {
    //         for (int j = 0; j < 10; j++) {
    //             CheckBox cb = new CheckBox();
    //             game.cellProperty(i, j).bindBidirectional(cb.selectedProperty());
    //             gridPane.add(cb, i, j);
    //         }
    //     }
    // }

}


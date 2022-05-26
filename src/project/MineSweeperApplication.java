/**
 *
 */
package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MineSweeperApplication extends Application {

    private MineSweeperController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MineSweeper");

        controller = new MineSweeperController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MineSweeperView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

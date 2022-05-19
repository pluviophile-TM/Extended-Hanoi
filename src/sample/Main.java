package sample;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        primaryStage.setTitle("Hanoi-Simulation");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        FadeTransition FadeIn = new FadeTransition(Duration.seconds(3),root);
        FadeIn.setFromValue(0);
        FadeIn.setToValue(1);
        FadeIn.setCycleCount(1);
        FadeIn.play();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

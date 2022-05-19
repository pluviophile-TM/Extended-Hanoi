package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WellcomeController {



    @FXML
    JFXTextField DiskNum;

    @FXML
    public void Click(){
        System.out.println("Click:[INVOKED]");
        if (DiskNum.getText().isEmpty()){
            System.out.println("Empty");
            return;
        }
        try {
            int num = Integer.parseInt(DiskNum.getText());
            System.out.println("NumberOfDisks:["+num+"]");
            Controller.NumberOfDisks=num;
        }catch (Exception e){
            System.out.println("Not-Number");
            return;
        }
        FadeTransition FadeOut = new FadeTransition(Duration.seconds(2),DiskNum.getParent());
        FadeOut.setFromValue(1);
        FadeOut.setToValue(0);
        FadeOut.setCycleCount(1);
        FadeOut.play();

        FadeOut.setOnFinished(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                DiskNum.getScene().setRoot(root);
                FadeTransition FadeIn = new FadeTransition(Duration.seconds(3),root);
                FadeIn.setFromValue(0);
                FadeIn.setToValue(1);
                FadeIn.setCycleCount(1);
                FadeIn.play();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }
}

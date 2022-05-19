package sample;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import org.w3c.dom.ls.LSOutput;

import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLOutput;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.regex.Matcher;

public class Controller implements Initializable {

    public static int NumberOfDisks;

    @FXML Cylinder Peg1;
    @FXML StackPane SamStack;
    @FXML Pane PaneSec;

    Stack<Disk> PegOneSt = new Stack<>();
    Stack<Disk> PegTwoSt = new Stack<>();
    Stack<Disk> PegThreeSt = new Stack<>();
    ArrayList<String> Commands = new ArrayList<>();

    boolean timerCheck=false;
    boolean level1 = true;
    boolean level2 = true;
    boolean level3 = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("PegOne-CheckList");
        System.out.println("-------------------------------------------------");
        update();
        System.out.printf("testPeg  " + PegOneSt.toString());
    }

    @FXML
    public void StartTimer() {
        System.out.println("Timer:[Started]");
    }

    @FXML
    public void Manual() {
        System.out.println("Manual:[INVOKED]");
        runCommand();
    }

    public void update() {
        System.out.println("update");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Disk disk;
                for (int i = 0; i < NumberOfDisks*3; i++) {
                    System.out.println("test" + i);
                   // Disk disk = new Disk(200 - (20 * i), 36, 110 + (i * 10), 393 - (i * 40), ++i);
                    if ((i+1)%3==1) {
                        disk = new Disk(200 - (i * 20), 36, 110 + (i* 10), 393 - ((i / 3) * 40), i+1 );
                        PegOneSt.push(disk);
                        PaneSec.getChildren().add(disk.STpane);
                        System.out.println(disk + "] i:" + i+1);
                    }
                    if ((i+1)%3==2){
                        disk = new Disk(200-(i*20),36,310+(i*10),393-((i/3)*40),i+1);
                        PegTwoSt.push(disk);
                        PaneSec.getChildren().add(disk.STpane);
                        System.out.println(disk + "] i:" + i+1);
                    }
                    if ((i+1)%3==0){
                        disk = new Disk(200-(i*20),36,500+(i*10),393-((i/3)*40),i+1);
                        PegThreeSt.push(disk);
                        PaneSec.getChildren().add(disk.STpane);
                        System.out.println(disk + "] i:" + i+1);
                    }

                }

                if (PegOneSt.size() == NumberOfDisks) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Peg1-Size:" + PegOneSt.size());
                    System.out.println("-------------------------------------------------");
                    System.out.println("Peg2-Size:" + PegTwoSt.size());
                    System.out.println("-------------------------------------------------");
                    System.out.println("Peg3-Size:" + PegThreeSt.size());
                } //remove it

                exHanoi(1, 2, 3, NumberOfDisks);

                for (int j = 0; j < Commands.size(); j++) {
                    System.out.println("Command[" + j + "] -> [" + Commands.get(j) + "]");
                } //remove it
                
            }

        });
    }

    public  void Hanoi(int A, int B, int C, int DiskNo) {
        if(DiskNo==1){
            Commands.add(A+","+C);
            return;
        }
        Hanoi(A, C, B, DiskNo - 1);
        Commands.add(A+","+C);
        Hanoi(B, A, C, DiskNo - 1);
    }

    public void exHanoi(int a,int b,int c,int n){
        if(n==1){
            Commands.add(c+","+b);
            Commands.add(a+","+c);
            Commands.add(b+","+a);
            Commands.add(b+","+c);
            Commands.add(a+","+c);
            return;
        }
        exHanoi(a,b,c,n-1);
        Hanoi(c,a,b,3*n-2);
        Commands.add(a+","+c);
        Hanoi(b,a,c,3*n-1);
    }

    public void reStart(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
            PaneSec.getScene().setRoot(root);
            FadeTransition FadeIn = new FadeTransition(Duration.seconds(3),root);
            FadeIn.setFromValue(0);
            FadeIn.setToValue(1);
            FadeIn.setCycleCount(1);
            FadeIn.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void toEnd(){
        while (PegOneSt.size()>0){
            PaneSec.getChildren().remove(PegOneSt.pop().STpane);
        }
        while (PegTwoSt.size()>0){
            PaneSec.getChildren().remove(PegTwoSt.pop().STpane);
        }
        while (PegThreeSt.size()>0){
            PaneSec.getChildren().remove(PegThreeSt.pop().STpane);
        }
        for(int i=0;i<NumberOfDisks*3;i++){
            Disk disk = new Disk(200 - (i * 20), 36, 500 + (i* 10), 393 - ((i) * 40), i+1 );
            PegThreeSt.push(disk);
            PaneSec.getChildren().add(disk.STpane);
        }
        return;
    }

    public void timerClicked() {
        timerCheck=!timerCheck;
        if(timerCheck)
            runCommand();
    }

    public void runCommand() {
        System.out.println("Command:[INVOKED] "+Commands.size());
        Disk temp = new Disk(0, 0, 0, 0, 0);
        if (Commands.size() > 0) {
            char Origin = Commands.get(0).charAt(0);
            char Dest = Commands.get(0).charAt(2);
            Commands.remove(0);
            switch (Origin) {
                case '1':
                    temp = PegOneSt.pop();
                    break;
                case '2':
                    temp = PegTwoSt.pop();
                    break;
                case '3':
                    temp = PegThreeSt.pop();
                    break;

            }
            System.out.println("Dest"+Dest);
            switch (Dest) {
                case '1':
                    Move(Origin - '0', 1, temp);
                    break;
                case '2':
                    Move(Origin - '0', 2, temp);
                    break;
                case '3':
                    System.out.println("checl");
                    Move(Origin - '0', 3, temp);
                    System.out.println(Origin + " origin");
                    break;

            }
        }
    }

    public void Move(int Origin, int Destination, Disk disk) {
        System.out.println("Movement:[Started]");

        if (Origin==1 && Destination==2){
            Right(disk,10,200,380-(PegTwoSt.size()*40));
            PegTwoSt.push(disk);
        }
        else if (Origin==1 && Destination==3){
            Right(disk,10,390,380-(PegThreeSt.size()*40));
            PegThreeSt.push(disk);
        }
        else if (Origin==2 && Destination==3){
            Right(disk,10,190,380-(PegThreeSt.size()*40));
            PegThreeSt.push(disk);
        }
        else if (Origin==2 && Destination==1){
            Left(disk,10,200,380-(PegOneSt.size()*40));
            PegOneSt.push(disk);
        }
        else if (Origin==3 && Destination==1){
            Left(disk,10,390,380-(PegOneSt.size()*40));
            PegOneSt.push(disk);
        }
        else if (Origin==3 && Destination==2){
            Left(disk,10,190,380-(PegTwoSt.size()*40));
            PegTwoSt.push(disk);
        }
    }

    public void Right(Disk disk,int up,int Right,int down){
        double InitialPosition = disk.STpane.getLayoutX();
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    disk.STpane.setLayoutY(disk.STpane.getLayoutY() - 5);
                    if (disk.STpane.getLayoutY() < up) {
                        stop();
                        AnimationTimer timer1 = new AnimationTimer() {
                            @Override
                            public void handle(long now) {
                                disk.STpane.setLayoutX(disk.STpane.getLayoutX()+5);
                                if ((-InitialPosition+disk.STpane.getLayoutX())==Right){
                                    stop();
                                    AnimationTimer timer2 = new AnimationTimer() {
                                        @Override
                                        public void handle(long now) {
                                            disk.STpane.setLayoutY(disk.STpane.getLayoutY()+5);
                                            if (disk.STpane.getLayoutY()>10+down){
                                                stop();
                                                if(timerCheck)
                                                   runCommand();
                                            }
                                        }
                                    };
                                    timer2.start();
                                }
                            }
                        };
                        timer1.start();
                    }
                }
            };
        timer.start();

    }

    public void Left(Disk disk,int up,int left,int down){
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    double InitialPosition = disk.STpane.getLayoutX();
                    disk.STpane.setLayoutY(disk.STpane.getLayoutY() - 5);
                    if (disk.STpane.getLayoutY() < up) {
                        stop();
                        AnimationTimer timer1 = new AnimationTimer() {
                            @Override
                            public void handle(long now) {
                                System.out.println(disk.STpane.getLayoutX());
                                disk.STpane.setLayoutX(disk.STpane.getLayoutX()-5);
                                if ((+InitialPosition-disk.STpane.getLayoutX())==left){
                                    stop();
                                    AnimationTimer timer2 = new AnimationTimer() {
                                        @Override
                                        public void handle(long now) {
                                          disk.STpane.setLayoutY(disk.STpane.getLayoutY()+5);
                                          if (disk.STpane.getLayoutY()>10+down){
                                              stop();
                                              if(timerCheck)
                                                  runCommand();
                                          }
                                        }
                                    };
                                    timer2.start();

                                }
                            }
                        };
                        timer1.start();
                    }
                }
            };
            timer.start();
        }

}









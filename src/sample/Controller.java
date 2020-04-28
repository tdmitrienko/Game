package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.GamePain;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    public Pane canvas1=new Pane();
    public GamePain gameP;
    public RadioButton easy=new RadioButton();
    public RadioButton medium=new RadioButton();
    public RadioButton hard=new RadioButton();
    public int millis=2000;
    public Label io;
    public Button start;
    public boolean flag=false;
    public boolean flag1=false;
    public Controller controller=this;
    public ArrayList<GamePain> ar=new ArrayList<>();

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    int kolTarak = 0;
    ToggleGroup group=new ToggleGroup();
    Image gameOver=new Image("/image/gameOver.png",600,500,false,false);
    ImageView gameO=new ImageView(gameOver);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       easy.setToggleGroup(group);
       easy.setSelected(true);
        medium.setToggleGroup(group);
        hard.setToggleGroup(group);

    }

    public  void startuem(ActionEvent event) {
        flag=false;
        canvas1.getChildren().remove(gameO);
        flag1= false;

       io.setVisible(false);
        easy.setVisible(false);
       medium.setVisible(false);
       hard.setVisible(false);
       start.setVisible(false);

       if(easy.isSelected()){
           millis=10000;
           Timer timer=new Timer();
           TimerTask timerTask= new TimerTask() {
                   @Override
               public void run() {
                       Platform.runLater(new Runnable() {
                           @Override
                           public void run() {
                               if(!flag){
                               gameP = new GamePain(canvas1, millis,controller);
                               ar.add(gameP);
                               gameP.tarakanP();
                               kolTarak++;
                               System.out.println("Tarak " +kolTarak);
                               }

                               else {
                                   if(!flag1){
                                   canvas1.getChildren().add(gameO);
                                   flag1=true;
                                   }
                                   for (GamePain gp:ar) {
                                       gp.stopPathT();
                                       canvas1.getChildren().remove(gp.getT());
                                   }
                                   start.setVisible(true);
                                   easy.setVisible(true);
                                   medium.setVisible(true);
                                   hard.setVisible(true);
                               }

                           }
                   });
           }
           };
           timer.schedule(timerTask, new Date(), 4000);
         }


      if(medium.isSelected()){
            millis=8000;
            Timer timer=new Timer();
            TimerTask timerTask= new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(!flag){
                                gameP = new GamePain(canvas1, millis,controller);
                                ar.add(gameP);
                                gameP.tarakanP();
                                kolTarak++;
                                System.out.println("Tarak " +kolTarak);
                            }

                            else {
                                if(!flag1){
                                    canvas1.getChildren().add(gameO);
                                    flag1=true;
                                }
                                for (GamePain gp:ar) {
                                    gp.stopPathT();
                                    canvas1.getChildren().remove(gp.getT());
                                }
                                start.setVisible(true);
                                easy.setVisible(true);
                                medium.setVisible(true);
                                hard.setVisible(true);
                            }
                        }
                    });
                }
            };
            timer.schedule(timerTask, new Date(), 3000);
        }


        if(hard.isSelected()){
            millis=5000;

            Timer timer=new Timer();
            TimerTask timerTask= new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(!flag){
                                gameP = new GamePain(canvas1, millis,controller);
                                ar.add(gameP);
                                gameP.tarakanP();
                                kolTarak++;
                                System.out.println("Tarak " +kolTarak);
                            }

                            else {
                                if(!flag1){
                                    canvas1.getChildren().add(gameO);
                                    flag1=true;
                                }
                                for (GamePain gp:ar) {
                                    gp.stopPathT();
                                    canvas1.getChildren().remove(gp.getT());
                                }
                                start.setVisible(true);
                                easy.setVisible(true);
                                medium.setVisible(true);
                                hard.setVisible(true);
                            }
                        }
                    });
                }
            };
            timer.schedule(timerTask, new Date(), 2000);
        }
    }
}

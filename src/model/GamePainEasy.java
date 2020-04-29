package model;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import sample.Controller;

import java.util.*;


public class GamePainEasy implements Active {
    private Pane canvas;
    private Controller controller;
    private PathTransition pathTransition = new PathTransition();
    private Path path;
    private Timer timer;
    private TimerTask timerTask;

    private ArrayList<Tarakan> list=new ArrayList<>();
    ImageView t;


    public ImageView getT() {
        return t;
    }

    public void stopPathT(){
        pathTransition.stop();
    }

    public GamePainEasy(Pane canvas, Controller controller) {
        this.canvas = canvas;
        this.controller = controller;
        timer=new Timer();
        timerTask= new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Tarakan tarakan = new Tarakan(canvas);
                        list.add(tarakan);
                        pathTransition(tarakan);
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 4000);
    }


    public void pathTransition(Tarakan tarakan) {
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.cancel();
                controller.gameOver();
                for (Tarakan t : list) {
                    t.stopPathTransition();
                    canvas.getChildren().remove(t);
                }
            }
        };

        Random r=new Random();
        int x1=60+r.nextInt(540);
        int x2=60+r.nextInt(540);
        int y1=400;
        int y2=-50;

        path=new Path();
        path.getElements().add(new MoveTo(x1,y1));
        path.getElements().add(new LineTo(x2,y2));
        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(10000));
        pathTransition.setPath(path);
        pathTransition.setNode(tarakan);
        pathTransition.setOnFinished(eventHandler);
        pathTransition.play();

        tarakan.tarakanOnClick(pathTransition);
    }
}
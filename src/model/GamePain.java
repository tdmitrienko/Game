package model;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import sample.Controller;

import java.util.Random;


public class GamePain  {
    private Pane canvas;
    private int millis;
    private PathTransition pathTransition;
    private Path path;
    private Controller controller;
    ImageView t;


    public ImageView getT() {
        return t;
    }

    public void stopPathT(){
        pathTransition.stop();
    }

    public GamePain(Pane canvas, int millis, Controller controller) {
        this.canvas = canvas;
        this.millis=millis;
        this.controller=controller;
    }


    public void tarakanP() {
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.setFlag(true);
            }
        };

        Random r=new Random();
        int x1=60+r.nextInt(540);
        int x2=60+r.nextInt(540);
        int y1=400;
        int y2=-100;

        Image tarakan=new Image("/image/tarakan.png",100,100,false,false);
        t=new ImageView(tarakan);

        path=new Path();
        path.getElements().add(new MoveTo(x1,y1));
        path.getElements().add(new LineTo(x2,y2));
        pathTransition=new PathTransition();
        pathTransition.setDuration(Duration.millis(millis));
        pathTransition.setPath(path);
        pathTransition.setNode(t);
        pathTransition.setOnFinished(eventHandler);
        pathTransition.play();

        canvas.getChildren().add(t);

        t.setOnMouseClicked(event -> {
            t.setImage(new Image("/image/bloot.png",100,100,false,false));
            pathTransition.pause();
            t.setX(event.getX()-50);
            t.setY(event.getY()-50);

        });

    }

}
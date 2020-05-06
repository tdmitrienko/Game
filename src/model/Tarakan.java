package model;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tarakan extends ImageView {

    private Pane pane;
    private PathTransition pathTransition;
    private Image tarakan=new Image("/image/tarakan.png",100,100,false,false);
    private Image blood =new Image("/image/bloot.png",100,100,false,false);

    public EventHandler<ActionEvent> ochkiAdd;

    public Tarakan(Pane pane, EventHandler<ActionEvent> ochkiAdd) {
        super();
        super.setImage(tarakan);
        this.ochkiAdd = ochkiAdd;
        this.pane = pane;
        pane.getChildren().add(this);
    }

    public void tarakanOnClick(PathTransition pathTransition){
        this.pathTransition = pathTransition;
        this.setOnMouseClicked(event -> {
            this.setImage(blood);
            pathTransition.pause();
            this.setX(event.getX()-50);
            this.setY(event.getY()-50);
            ochkiAdd.handle(new ActionEvent());
            this.setOnMouseClicked(event1 -> { });
        });

    }

    public void stopPathTransition() {
        pathTransition.stop();
    }
}

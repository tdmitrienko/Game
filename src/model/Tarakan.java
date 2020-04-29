package model;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tarakan extends ImageView {

    private Pane pane;
    private PathTransition pathTransition;
    private Image tarakan=new Image("/image/tarakan.png",100,100,false,false);
    private Image blood =new Image("/image/bloot.png",100,100,false,false);

    public Tarakan(Pane pane) {
        super();
        super.setImage(tarakan);
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
            this.setOnMouseClicked(event1 -> { });
        });

    }

    public void stopPathTransition() {
        pathTransition.stop();
    }
}

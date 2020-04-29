package model;

import javafx.scene.image.ImageView;

public interface Active {
    void pathTransition(Tarakan tarakan);
    void stopPathT();
    ImageView getT();
}

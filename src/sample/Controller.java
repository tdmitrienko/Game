package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.*;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    public Pane canvas1 = new Pane();
    public RadioButton easy = new RadioButton();
    public RadioButton medium = new RadioButton();
    public RadioButton hard = new RadioButton();
    public Label io;
    public Button start;
    public Active active;
    public EventHandler<ActionEvent> gameOverAction;
    public Button vixod;

    ToggleGroup group = new ToggleGroup();
    Image gameOver;
    ImageView gameO;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        easy.setToggleGroup(group);
        easy.setSelected(true);
        medium.setToggleGroup(group);
        hard.setToggleGroup(group);
        vixod.setVisible(false);
        gameOverAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameOver = new Image("/image/gameOver.png", 700, 500, false, false);
                gameO = new ImageView(gameOver);
                start.setVisible(true);
                easy.setVisible(true);
                medium.setVisible(true);
                hard.setVisible(true);
                canvas1.getChildren().remove(gameO);
                canvas1.getChildren().add(gameO);
            }
        };
    }

    public void startuem(ActionEvent event) {
        vixod.setVisible(true);
        canvas1.getChildren().remove(gameO);
        canvas1.setCursor(new ImageCursor(new Image("/image/tapok.png",1500,1500,false,false)));
        io.setVisible(false);
        easy.setVisible(false);
        medium.setVisible(false);
        hard.setVisible(false);
        start.setVisible(false);

        if (easy.isSelected()) {
            active = new GamePainEasy(canvas1, gameOverAction);
        }
        else if (medium.isSelected()) {
            active = new GamePainMedium(canvas1, gameOverAction);
        }
        else {
            active = new GamePainHard(canvas1, gameOverAction);
        }
    }

    public void exitPlay(ActionEvent event) {
        vixod.setVisible(false);
        active.exitGame();
        start.setVisible(true);
        easy.setVisible(true);
        medium.setVisible(true);
        hard.setVisible(true);
    }
}

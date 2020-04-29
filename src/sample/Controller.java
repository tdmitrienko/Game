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
    public Controller controller = this;
    public Active active;

    ToggleGroup group = new ToggleGroup();
    Image gameOver;
    ImageView gameO;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        easy.setToggleGroup(group);
        easy.setSelected(true);
        medium.setToggleGroup(group);
        hard.setToggleGroup(group);

    }

    public void gameOver() {
        gameOver = new Image("/image/gameOver.png", 600, 500, false, false);
        gameO = new ImageView(gameOver);
        start.setVisible(true);
        easy.setVisible(true);
        medium.setVisible(true);
        hard.setVisible(true);
        canvas1.getChildren().remove(gameO);
        canvas1.getChildren().add(gameO);
    }

    public void startuem(ActionEvent event) {
        canvas1.getChildren().remove(gameO);

        io.setVisible(false);
        easy.setVisible(false);
        medium.setVisible(false);
        hard.setVisible(false);
        start.setVisible(false);

        if (easy.isSelected()) {
            active = new GamePainEasy(canvas1, controller);
        }
        else if (medium.isSelected()) {
            active = new GamePainMedium(canvas1, controller);
        }
        else {
            active = new GamePainHard(canvas1, controller);
        }
    }
}

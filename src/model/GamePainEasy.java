package model;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.Controller;
import java.util.*;


public class GamePainEasy implements Active {
    private Pane canvas;
    private PathTransition pathTransition = new PathTransition();
    private Path path;
    private Timer timer=new Timer();;
    private TimerTask timerTask;
    private EventHandler<ActionEvent> gameOver;
    private ArrayList<Tarakan> list=new ArrayList<>();
    ImageView t;
    private int ochki=0;
    private Text text = new Text("");



    public ImageView getT() {
        return t;
    }

    @Override
    public void exitGame() {
        timer.cancel();
        for (Tarakan tarakan:list) {
            canvas.getChildren().remove(tarakan);
            tarakan.stopPathTransition();
        }
        canvas.getChildren().remove(text);
    }

    public GamePainEasy(Pane canvas, EventHandler<ActionEvent> gameOver) {
        this.canvas = canvas;
        this.gameOver = gameOver;

        timerTask= new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Tarakan tarakan = new Tarakan(canvas, new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                ochki+=1;
                                canvas.getChildren().remove(text);
                                text.setText("" + ochki);
                                canvas.getChildren().add(text);
                            }
                        });
                        list.add(tarakan);
                        pathTransition(tarakan);
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 4000);
        text.setX(50);
        text.setY(50);
        text.setFont(new Font(50));
        text.setFill(Color.RED);
    }

    public void pathTransition(Tarakan tarakan) {
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
        pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.cancel();
                gameOver.handle(event);
                canvas.getChildren().remove(text);
                for (Tarakan t : list) {
                    t.stopPathTransition();
                    canvas.getChildren().remove(t);
                }
            }
        });
        pathTransition.play();

        tarakan.tarakanOnClick(pathTransition);
    }

}
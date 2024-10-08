package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import nz.ac.auckland.se206.App;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.beans.binding.DoubleBinding;

public class mainController {
    @FXML private Pane scalePane;
    @FXML private Rectangle box1;
    @FXML private Rectangle box2;
    @FXML private Rectangle box3;
    @FXML private Rectangle box4;
    @FXML private Rectangle box5;
    @FXML private Rectangle box6;
    @FXML private Rectangle box7;
    @FXML private Rectangle box8;
    @FXML private Rectangle box9;
    @FXML private Rectangle backRectangle;
    @FXML private Text lbl1;
    @FXML private Text lbl2;
    @FXML private Text lbl3;
    @FXML private Text lbl4;
    @FXML private Text lbl5;
    @FXML private Text lbl6;
    @FXML private Text lbl7;
    @FXML private Text lbl8;
    @FXML private Text lbl9;
    @FXML private Text lblAi;
    @FXML private Label turnLabel;
    @FXML private Text lblX;
    @FXML private Text lblO;
    @FXML private Button switchBtn;
    @FXML private Button resetBtn;


    private ArrayList<Integer> Xints = new ArrayList<Integer>();
    private ArrayList<Integer> Oints = new ArrayList<Integer>();
    private boolean gameOver = false;
    private int Xscore = 0;
    private int Oscore = 0;
    private String mode = "PvP";

    private enum Player {
        X, O
    }
    Player currentPlayer = Player.X;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            lblAi.setVisible(false);
            turnLabel.setText("Player X");
            for (int i = 1; i < 10; i++) {
                Text lbl = (Text) returnElement("lbl", i);
                lbl.setText("");
            }
            Scene scene = App.getScene();

            scalePane.layoutXProperty().bind(scene.widthProperty().subtract(385).divide(2));
            scalePane.layoutYProperty().bind(scene.heightProperty().subtract(500).divide(2));
            DoubleBinding scale = new DoubleBinding() {
                {
                    super.bind(scene.widthProperty(), scene.heightProperty());
                }

                @Override
                protected double computeValue() {
                    return Math.min(scene.widthProperty().doubleValue() / 431, scene.heightProperty().doubleValue() / 469);
                }
            };
            scalePane.scaleXProperty().bind(scale);
            scalePane.scaleYProperty().bind(scale);
        });
    }

    @FXML
    public Object returnElement(String name, int id) {
        String nameString = name + id;
        Object element = App.getScene().lookup("#" + nameString);
        return element;
    }

    @FXML
    public void checkVictory() {
        if (Xints.contains(1) && Xints.contains(2) && Xints.contains(3)) {
            turnLabel.setText("X wins!");
        } else if (Xints.contains(4) && Xints.contains(5) && Xints.contains(6)) {
            turnLabel.setText("X wins!");
        } else if (Xints.contains(7) && Xints.contains(8) && Xints.contains(9)) {
            turnLabel.setText("X wins!");
        } else if (Xints.contains(1) && Xints.contains(4) && Xints.contains(7)) {
            turnLabel.setText("X wins!");
        } else if (Xints.contains(2) && Xints.contains(5) && Xints.contains(8)) {
            turnLabel.setText("X wins!");
        } else if (Xints.contains(3) && Xints.contains(6) && Xints.contains(9)) {
            turnLabel.setText("X wins!");
        } else if (Xints.contains(1) && Xints.contains(5) && Xints.contains(9)) {
            turnLabel.setText("X wins!");
        } else if (Xints.contains(3) && Xints.contains(5) && Xints.contains(7)) {
            turnLabel.setText("X wins!");
        }
        if (Oints.contains(1) && Oints.contains(2) && Oints.contains(3)) {
            turnLabel.setText("O wins!");
        } else if (Oints.contains(4) && Oints.contains(5) && Oints.contains(6)) {
            turnLabel.setText("O wins!");
        } else if (Oints.contains(7) && Oints.contains(8) && Oints.contains(9)) {
            turnLabel.setText("O wins!");
        } else if (Oints.contains(1) && Oints.contains(4) && Oints.contains(7)) {
            turnLabel.setText("O wins!");
        } else if (Oints.contains(2) && Oints.contains(5) && Oints.contains(8)) {
            turnLabel.setText("O wins!");
        } else if (Oints.contains(3) && Oints.contains(6) && Oints.contains(9)) {
            turnLabel.setText("O wins!");
        } else if (Oints.contains(1) && Oints.contains(5) && Oints.contains(9)) {
            turnLabel.setText("O wins!");
        } else if (Oints.contains(3) && Oints.contains(5) && Oints.contains(7)) {
            turnLabel.setText("O wins!");
        }
        if (turnLabel.getText().equals("X wins!")) {
            gameOver = true;
            Xscore++;
            lblX.setText("X score: " + Xscore);
        }
        if (turnLabel.getText().equals("O wins!")) {
            gameOver = true;
            Oscore++;
            lblO.setText("O score: " + Oscore);
        }
    }

    @FXML
    private void onClick(MouseEvent event) {
        if (!gameOver) {
            if (mode.equals("PvP")) {                
                Rectangle box = (Rectangle) event.getSource();
                int id = Integer.parseInt(box.getId().substring(3));
                if (Xints.contains(id) || Oints.contains(id)) {
                    return;
                }
                Text lbl = (Text) returnElement("lbl", id);
                if (currentPlayer == Player.X) {
                    currentPlayer = Player.O;
                    lbl.setText("X");
                    turnLabel.setText("Player O");
                    Xints.add(id);
                    if (Oints.size() == 3) {
                        lbl = (Text) returnElement("lbl", Oints.get(0));
                        lbl.setOpacity(0.5);
                    }
                } else {
                    currentPlayer = Player.X;
                    lbl.setText("O");
                    turnLabel.setText("Player X");
                    Oints.add(id);
                    if (Xints.size() == 3) {
                        lbl = (Text) returnElement("lbl", Xints.get(0));
                        lbl.setOpacity(0.5);
                    }
                }
                checkNumber();
                checkVictory();
            } else {
                Rectangle box = (Rectangle) event.getSource();
                int id = Integer.parseInt(box.getId().substring(3));
                if (Xints.contains(id) || Oints.contains(id)) {
                    return;
                }
                Text lbl = (Text) returnElement("lbl", id);
                if (currentPlayer == Player.X) {
                    currentPlayer = Player.O;
                    lbl.setText("X");
                    turnLabel.setText("Player O");
                    Xints.add(id);
                    if (Oints.size() == 3) {
                        lbl = (Text) returnElement("lbl", Oints.get(0));
                        lbl.setOpacity(0.5);
                    }
                    checkNumber();
                    checkVictory();
                    onAi();
                }
            }
        }
    }

    @FXML
    private void onAi() {
        if (!gameOver) {
            Ai ai = new Ai();
            int move = ai.getMove(Oints, Xints);
            Text lbl = (Text) returnElement("lbl", move);
            lbl.setText("O");
            Oints.add(move);
            currentPlayer = Player.X;
            turnLabel.setText("Player X");
            if (Xints.size() == 3) {
                lbl = (Text) returnElement("lbl", Xints.get(0));
                lbl.setOpacity(0.5);
            }
            checkNumber();
            checkVictory();
        }
    }

    @FXML
    private void checkNumber() {
        if (Xints.size() == 4) {
            Text lbl = (Text) returnElement("lbl", Xints.get(0));
            lbl.setText("");
            Xints.remove(0);
            lbl.setOpacity(1);
        }
        if (Oints.size() == 4) {
            Text lbl = (Text) returnElement("lbl", Oints.get(0));
            lbl.setText("");
            Oints.remove(0);
            lbl.setOpacity(1);
        }
    }

    @FXML
    private void onReset() {
        gameOver = false;
        currentPlayer = Player.X;
        Xints.clear();
        Oints.clear();
        for (int i = 1; i < 10; i++) {
            Text lbl = (Text) returnElement("lbl", i);
            lbl.setText("");
            lbl.setOpacity(1);
        }
        if (currentPlayer == Player.X) {
            turnLabel.setText("Player X");
        } else {
            turnLabel.setText("Player O");
        }
    }

    @FXML
    private void onSwitch() {
        if (mode.equals("PvP")) {
            mode = "PvAI";
            switchBtn.setText("PvP");
            lblAi.setVisible(true);
        } else {
            mode = "PvP";
            switchBtn.setText("PvAI");
            lblAi.setVisible(false);
        }
        onReset();
        lblX.setText("X score: 0");
        lblO.setText("O score: 0");
    }
}

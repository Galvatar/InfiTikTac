package nz.ac.auckland.se206.controllers;

import java.util.ArrayList;
import nz.ac.auckland.se206.App;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class todoController {
    public static ArrayList<Tasks> tasks = new ArrayList<Tasks>();
    private ArrayList<Tasks> completedTasks = new ArrayList<Tasks>();
    @FXML private TextField addField;
    @FXML private Label clickedLabel;
    @FXML private int id;
    @FXML private TextArea completedArea = (TextArea) App.scene.lookup("#completedArea");

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            for (int i = 0; i < tasks.size(); i++) {
                String string = "label" + (i + 1);
                clickedLabel = (Label) App.scene.lookup("#" + string);
                clickedLabel.setText(tasks.get(i).getTask());
            }
        });
    }

    @FXML
    public void onSwitchTracker() {
        try {
            App.setRoot("main");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumber(String id) {
        for (int j = 0; j < id.length(); j++) {
            if (id.charAt(j) >= '0' && id.charAt(j) <= '9') {
                return id.charAt(j) - '0';
            }
        }
        return -1;
    }

    @FXML
    public void onClick(MouseEvent event) {
        clickedLabel = (Label) event.getSource();
        id = getNumber(clickedLabel.getId());
        String idString = "label" + id;
        clickedLabel = (Label) App.scene.lookup("#" + idString);
        String task = clickedLabel.getText();
        for (Tasks t : tasks) {
            if (t.getTask().equals(task)) {
                t.setStatus(Tasks.Status.COMPLETED);
                completedTasks.add(t);
                completedArea.appendText(t.getTask() + "\n");
                tasks.remove(t);
                break;
            }
        }
        int i;
        for (i = 0; i < tasks.size(); i++) {
            idString = "label" + (i + 1);
            clickedLabel = (Label) App.scene.lookup("#" + idString);
            clickedLabel.setText(tasks.get(i).getTask());
        }
        idString = "label" + (i + 1);
        clickedLabel = (Label) App.scene.lookup("#" + idString);
        clickedLabel.setText("");
    }

    @FXML
    public void onAdd() {
        addField = (TextField) App.scene.lookup("#addField");
        String message = " - " + addField.getText().trim();
        if (tasks.size() == 15) {
            addField.clear();
            return;
        }
        Tasks temp = new Tasks(message);
        tasks.add(temp);
        addField.clear();
        int size = tasks.size();
        String idString = "label" + size;
        clickedLabel = (Label) App.scene.lookup("#" + idString);
        clickedLabel.setText(message);
    }

    @FXML
    private void onClear() {
        tasks.clear();
        completedArea.clear();
        completedTasks.clear();
        for (int i = 0; i < 15; i++) {
            String idString = "label" + (i + 1);
            clickedLabel = (Label) App.scene.lookup("#" + idString);
            clickedLabel.setText("");
        }
    }
}

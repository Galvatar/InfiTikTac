package nz.ac.auckland.se206.controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;

public class mainController {
    @FXML TextField name1;
    @FXML TextField name2;
    @FXML TextField name3;
    @FXML TextField name4;
    @FXML TextField name5;
    @FXML TextField add1;
    @FXML TextField add2;
    @FXML TextField add3;
    @FXML TextField add4;
    @FXML TextField add5;
    @FXML ProgressBar bar1;
    @FXML ProgressBar bar2;
    @FXML ProgressBar bar3;
    @FXML ProgressBar bar4;
    @FXML ProgressBar bar5;
    @FXML Label percentage1;
    @FXML Label percentage2;
    @FXML Label percentage3;
    @FXML Label percentage4;
    @FXML Label percentage5;
    @FXML Label hrsWeek;
    @FXML Label hrsAll;
    @FXML Label hrsMore;
    @FXML Label hrsExtra;
    private int id;
    private TextField clickedField;
    public static ArrayList<Subject> subjects = new ArrayList<Subject>();
    private double totalHours = 0;
    private double totalHoursIndividual = 0;
    private double extraHours = 0;

    public void initialize() {
        for (int i = 0; i < 5; i++) {
            subjects.add(new Subject());
        }
        Platform.runLater(() -> {
            setUi();
        });
    }

    public void setUi() {
        for (int i = 0; i < 5; i++) {
            String name = "name" + (i+1);
            TextField nameField = (TextField) name1.getScene().lookup("#" + name);
            nameField.setText(subjects.get(i).getName());
            subjects.get(i).setTime(Double.toString(subjects.get(i).getTotalHours()));
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

    public void onRecordClick(MouseEvent event) {
        clickedField = (TextField) event.getSource();
        id = getNumber(clickedField.getId());
    }

    public void onSetTitle() {
        String message = clickedField.getText().trim();
        if (message.isEmpty()||message.length()>11) {
            clickedField.setText("Invalid");
            return;
        }
        String upperCased = message.toUpperCase();
        subjects.get(id-1).setName(upperCased);
        clickedField.setText(upperCased);
    }

    public void onAdd() {
        String message = clickedField.getText().trim();
        Boolean result = subjects.get(id-1).setTime(message);
        if (!result) {
            clickedField.setText("Invalid");
            return;
        }
        clickedField.clear();
        String name = "bar" + id;
        String name2 = "percentage" + id;
        ProgressBar bar = (ProgressBar) clickedField.getScene().lookup("#" + name);
        Label percentage = (Label) clickedField.getScene().lookup("#" + name2);
        bar.setProgress(subjects.get(id-1).getZeroToOne());
        percentage.setText(subjects.get(id-1).getPercentage() + "%");
        totalHours += subjects.get(id-1).getHours();
        extraHours += subjects.get(id-1).getExtra();
        hrsWeek.setText("Total hours this week: " + Math.round(totalHours * 100)/100);
        hrsExtra.setText("Extra hours worked this week: " + Math.round(extraHours * 100)/100);
        if (subjects.get(id-1).getTotalHours() <= 10) {
            totalHoursIndividual += subjects.get(id-1).getHours();
        } else {
            totalHoursIndividual += subjects.get(id-1).getHours() - (subjects.get(id-1).getExtra());
        }
        if (totalHoursIndividual > 40) {
            hrsMore.setText("Congratulations, you completed your 40 hours!");
        } else {
            hrsMore.setText("Just " + Math.round((40 - totalHoursIndividual) * 100)/100 + " more hours to go!");
        }
    }
}

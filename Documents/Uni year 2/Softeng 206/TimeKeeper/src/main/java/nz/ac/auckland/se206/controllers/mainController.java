package nz.ac.auckland.se206.controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;

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
    private int hoursRequired = 0;
    public static double allTimeHours = 0;
    private double totalHours = 0;
    private double totalHoursIndividual = 0;
    private double extraHours = 0;

    public void initialize() {
        for (int i = 0; i < 5; i++) {
            subjects.add(new Subject());
        }
        Platform.runLater(() -> {
            setUi(0, 5);
        });
    }

    public void setUi(int min, int max) {
        for (int i = min; i < max; i++) {
            String name = "name" + (i+1);
            String bar = "bar" + (i+1);
            String percentage = "percentage" + (i+1);
            Label percentageField = (Label) name1.getScene().lookup("#" + percentage);
            percentageField.setText(subjects.get(i).getPercentage() + "%");
            ProgressBar barField = (ProgressBar) name1.getScene().lookup("#" + bar);
            barField.setProgress(subjects.get(i).getZeroToOne());
            TextField nameField = (TextField) name1.getScene().lookup("#" + name);
            nameField.setText(subjects.get(i).getName());
            totalHours += subjects.get(i).getHours();
            extraHours += subjects.get(i).getExtra();
            hrsAll.setText("Total hours all time: " + Math.round(allTimeHours * 100)/100);
            hrsWeek.setText("Total hours this week: " + Math.round(totalHours * 100)/100);
            hrsExtra.setText("Extra hours worked this week: " + Math.round(extraHours * 100)/100);
            int count = 0;
            for (int j = 0; j < 5; j++) {
                if (!(subjects.get(j).getName().equals(""))) {
                    count++;
                }
            }
            hoursRequired = count * 10;
            if (subjects.get(i).getTotalHours() <= 10) {
                totalHoursIndividual += subjects.get(i).getHours();
            } else {
                totalHoursIndividual += subjects.get(i).getHours() - (subjects.get(i).getExtra());
            }
            if (totalHoursIndividual >= hoursRequired) {
                hrsMore.setText("Congratulations, you completed your " + hoursRequired + " hours!");
            } else {
                hrsMore.setText("Just " + Math.round((hoursRequired - totalHoursIndividual) * 100)/100 + " more hours to go!");
            }
        }
    }

    public void onKeyPressed(KeyEvent event) {
        String keyPressed = event.getText();
        if ((keyPressed.equals("\t")) && clickedField.getId().contains("name")) {
            onSetTitle();
            id++;
            if (id > 5) {
                id = 1;
                clickedField = (TextField) name1.getScene().lookup("#add1");
            } else {
                String idString = "name" + id;
                clickedField = (TextField) name1.getScene().lookup("#" + idString);
            }
        } else if ((keyPressed.equals("\t")) && clickedField.getId().contains("add")) {
            onAdd();
            id++;
            String idString = "add" + id;
            clickedField = (TextField) name1.getScene().lookup("#" + idString);
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
        if (clickedField != null && clickedField.getId().contains("name")) {
            onSetTitle();
        }
        clickedField = (TextField) event.getSource();
        id = getNumber(clickedField.getId());
    }

    public void onSetTitle() {
        String message = clickedField.getText().trim();
        if (message.length()>11) {
            clickedField.clear();
            return;
        }
        String upperCased = message.toUpperCase();
        subjects.get(id-1).setName(upperCased);
        clickedField.setText(upperCased);
        setUi(id-1,id);
    }

    public void onReset() {
        ArrayList<Subject> temp = new ArrayList<Subject>();
        for (int i = 0; i < 5; i++) {
            Subject subject = new Subject();
            temp.add(subject);
        }
        subjects.clear();
        for (Subject subject : temp) {
            subjects.add(subject);
        }
        totalHours = 0;
        totalHoursIndividual = 0;
        extraHours = 0;
        allTimeHours = 0;
        temp.clear();
        setUi(0,5);
    }

    public void onAdd() {
        String message = clickedField.getText().trim();
        Boolean result = subjects.get(id-1).setTime(message);
        if (!result) {
            clickedField.clear();
            return;
        }
        clickedField.clear();
        allTimeHours += subjects.get(id-1).getHours();
        setUi(id-1, id);
        
    }

    public void onClear() {
        ArrayList<Subject> temp = new ArrayList<Subject>();
        for (int i = 0; i < 5; i++) {
            Subject subject = new Subject();
            subject.setName(subjects.get(i).getName());
            temp.add(subject);
        }
        subjects.clear();
        for (Subject subject : temp) {
            subjects.add(subject);
        }
        totalHours = 0;
        totalHoursIndividual = 0;
        extraHours = 0;
        temp.clear();
        setUi(0, 5);
    }
}

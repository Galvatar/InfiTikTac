package nz.ac.auckland.se206.controllers;

import nz.ac.auckland.se206.App;

public class todoController {
    public void onSwitchTracker() {
        try {
            App.setRoot("main");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

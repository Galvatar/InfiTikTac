package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import nz.ac.auckland.se206.controllers.Subject;
import nz.ac.auckland.se206.controllers.mainController;

public class App extends Application {
  private static Scene scene;
  private int count = 0;
  private int count2 = 0;

  public static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
    return fxmlLoader.load();
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = loadFXML("main");
    
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    stage.setTitle("TimeKeeper");
    root.requestFocus();

    stage.setOnCloseRequest(event -> {
      onExit();
    });

    initialize();
  }

  public void initialize() {
    try {
      File file = new File("savedata.txt");
      if (!file.exists()) {
        return;
      }
      // Create a FileReader object
      FileReader fileReader = new FileReader("savedata.txt");

      // Create a BufferedReader object
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      // Read the file line by line
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        if (count2 == 5) {
          mainController.allTimeHours = Integer.parseInt(line);
          break;
        }
        if (count == 0) {
          mainController.subjects.get(count2).setName(line);
          count++;
        } else if (count == 1) {
          mainController.subjects.get(count2).setDoubleTime(Double.parseDouble(line));
          count++;
        } else if (count == 2) {
          mainController.subjects.get(count2).setOldTime(Double.parseDouble(line));
          count++;
        } else if (count == 3) {
          mainController.subjects.get(count2).setZeroToOne(Double.parseDouble(line));
          count++;
        } else if (count == 4) {
          mainController.subjects.get(count2).setPercentage(Integer.parseInt(line));
          count = 0;
          count2++;
        }
      }

      // Close the BufferedReader
      bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  public void onExit() {
    try {
      File file = new File("savedata.txt");
      file.delete();
      // Create a FileWriter object
      FileWriter fileWriter = new FileWriter("savedata.txt");

      // Create a PrintWriter object
      PrintWriter printWriter = new PrintWriter(fileWriter);

      for (int i = 0; i < 5; i++) {
        printWriter.println(mainController.subjects.get(i).getName());
        printWriter.println(mainController.subjects.get(i).getTime());
        printWriter.println(mainController.subjects.get(i).getOldTime());
        printWriter.println(mainController.subjects.get(i).getZeroToOne());
        printWriter.println(mainController.subjects.get(i).getPercentage());
      }
      printWriter.println(mainController.allTimeHours);

      // Close the PrintWriter
      System.out.println("File saved");
      printWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

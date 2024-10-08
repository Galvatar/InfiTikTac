package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import nz.ac.auckland.se206.controllers.mainController;
import nz.ac.auckland.se206.controllers.todoController;
import nz.ac.auckland.se206.controllers.Tasks;

public class App extends Application {
  public static Scene scene;
  private int count = 0;
  private int count2 = 0;

  public static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void setRoot(String fxml) throws IOException {
    try {
      scene.setRoot(loadFXML(fxml));
    } catch (IOException e) {
      e.printStackTrace();
  }
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = loadFXML("main");
    
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    stage.setResizable(false);
    stage.setTitle("TimeKeeper");
    stage.getIcons().add(new javafx.scene.image.Image(App.class.getResourceAsStream("/images/Timekeeper.png")));
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
        if (count == 6) {
          todoController.tasks.add(new Tasks(line));
        } else {
          if (count2 == 5) {
            mainController.allTimeHours = Double.parseDouble(line);
            count = 6;
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
      }

      // Close the BufferedReader
      bufferedReader.close();
    } catch (Exception e) {
      e.printStackTrace();
      // File file = new File("savedata.txt");
      // file.delete();
      return;
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
      for (int i = 0; i < todoController.tasks.size(); i++) {
        printWriter.println(todoController.tasks.get(i).getTask());
      }

      // Close the PrintWriter
      System.out.println("File saved");
      printWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

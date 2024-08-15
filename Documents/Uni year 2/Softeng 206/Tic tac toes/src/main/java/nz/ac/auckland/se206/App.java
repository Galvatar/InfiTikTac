package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  private static Scene scene;

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
      stage.setResizable(false);
      stage.setTitle("Infinite Tic Tac Toe");
      root.requestFocus();
    }

    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import structure.SongList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/SonglibUI.fxml"));
        primaryStage.setTitle("Songlib");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    private void closeProgram(){
        System.out.println("close!!!!!!!");
    }

    public static void main(String[] args) {
        launch(args);

    }
}

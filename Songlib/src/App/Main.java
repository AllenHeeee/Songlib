package App;

import Structure.SongList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/UI.fxml"));
        primaryStage.setTitle("Songlib");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->closeProgram());
    }

    private void closeProgram(){
        System.out.println("close!!!!!!!");
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package App;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import structure.SongList;
import View.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/SonglibUI.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
        try {
            SongList.fildRead();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
       Controller.start(primaryStage);
        /*
        private void closeProgram(){
            System.out.println("close!!!!!!!");
        }*/
    }
    public void main(String[] args) {
        launch(args);
        for(int i=0;i<SongList.list.size();i++)     //读取txt中arraylist测试组
        {
            String str2 = SongList.list.get(i).getSongName();
            System.out.println(str2);
            str2 = SongList.list.get(i).getArtist();
            System.out.println(str2);
            str2 = SongList.list.get(i).getAlbum();
            System.out.println(str2);
            String str = SongList.list.get(i).getYear();
            System.out.println(str);
        }
    }
}

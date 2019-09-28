
package View;

import com.sun.glass.ui.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import structure.Song;
import structure.SongList;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller{
    @FXML Label SongNameShow;
    @FXML TextField SongNameAdd;
    @FXML TextField AritstAdd;
    @FXML TextField AlbumAdd;
    @FXML TextField YearAdd;
    @FXML TextField SongNameEdit;
    @FXML TextField ArtistEdit;
    @FXML TextField AlbumEdit;
    @FXML TextField YearEdit;
    @FXML ListView<String> SongListUI;               //
    @FXML Label NoticeLabel;
    private ObservableList<String> obsList;
    public void LastSongTapped(ActionEvent e) {

    }
    public void NextSongTapped(ActionEvent e) {

    }
    public void ExitTapped(ActionEvent e) {

    }
    public void EditTapped(ActionEvent e) {

    }
    public void DeleteTapped(ActionEvent e) {

    }
    public void ChooseTapped(ActionEvent e) {
    }

    public static void start(Stage mainStage)  {
        obsList=FXCollections.observableArrayList();
        obsList.removeAll(obsList);
        for(int i=0;i<SongList.list.size();i++)
        {
            String str=SongList.list.get(i).getSongName()+"          By:"+SongList.list.get(i).getArtist();
            obsList.addAll(str);
            SongListUI.getItems().addAll(obsList);
        }
    }

    public void AddTapped(ActionEvent e) {
        String songname=SongNameAdd.getText();
        String artist=AritstAdd.getText();
        String Album=AlbumAdd.getText();

   //     String ShowInit=null;                   //
       int year=0;
       String yearStr=YearAdd.getText();
        try {
            year=Integer.parseInt(YearAdd.getText());
        }catch (Exception ex){
            showInputError();
            return;
        }

        if(songname.trim().isEmpty()||artist.trim().isEmpty()||Album.trim().isEmpty()||year<0||year>2019){
            showInputError();
            NoticeLabel.setText("Add Failed!");
            System.out.println("Add Failed!");
            return;
        }
    //    infolist.removeAll(infolist);           //
/*
        for(int i=0;i<SongList.listforRe.size();i++)
        {
          //  ShowInit=SongList.listforRe.
            System.out.println(SongList.listforRe.size());
        }*/

        SongList.list.add(new Song(songname,artist,Album,yearStr));
        /*
        for(int i=0;i<SongList.list.size();i++)
        {
            Song listprint=new Song<SongList.list.getSongName()>       //
            infolist.addAll(ShowStr);                 //
            SongListUI.getItems().addAll(infolist);            //
        }
*/
        try{
            SongList.loadListIntoFile();
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
        NoticeLabel.setText("");
        SongNameAdd.setText("");
        AritstAdd.setText("");
        AlbumAdd.setText("");
        YearAdd.setText("");
        NoticeLabel.setText("Add Succeed!");
        System.out.println("Add Succeed!");
    }
    public static void showInputError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Add Failed");
        alert.setContentText("Bad Input!");
        alert.showAndWait();
    }


}
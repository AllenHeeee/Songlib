
package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import structure.Song;
import structure.SongList;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {    //   修改过
    ObservableList obslist= FXCollections.observableArrayList();   //   修改过
    @FXML Label SongNameShow;
    @FXML TextField SongNameAdd;
    @FXML TextField AritstAdd;
    @FXML TextField AlbumAdd;
    @FXML TextField YearAdd;
    @FXML TextField SongNameEdit;
    @FXML TextField ArtistEdit;
    @FXML TextField AlbumEdit;
    @FXML TextField YearEdit;
    @FXML ListView<String> SongListUI;               //   修改过
    @FXML Label NoticeLabel;
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
    public void AddTapped(ActionEvent e) {
        String songname=SongNameAdd.getText();
        String artist=AritstAdd.getText();
        String Album=AlbumAdd.getText();
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


        SongList.list.add(new Song(songname,artist,Album,yearStr));

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
        SongListUI.getItems().clear();    //   从这里开始
        loadData();
        SongNameEdit.setText(songname);
        ArtistEdit.setText(artist);
        AlbumEdit.setText(Album);
        YearEdit.setText(yearStr);
        SongListUI.getSelectionModel().select(songname+"   By:"+artist);     //到这里结束
    }
    public static void showInputError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Add Failed");
        alert.setContentText("Bad Input!");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {     //  从这开始
        try{
            SongList.fildRead();
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
        if(SongList.list.size()!=0){
        loadData();
        SongListUI.getSelectionModel().select(0);
        if(SongList.list.get(0).getSongName()!=null) {
            SongNameEdit.setText(SongList.list.get(0).getSongName());
            ArtistEdit.setText(SongList.list.get(0).getArtist());
            AlbumEdit.setText(SongList.list.get(0).getAlbum());
            YearEdit.setText(SongList.list.get(0).getYear());
        }
        }
    }
    private void loadData(){
        obslist.removeAll(obslist);
        String str=null;
        for(int i=0;i<SongList.list.size();i++)
        {
            str=SongList.list.get(i).getSongName()+"   By:"+SongList.list.get(i).getArtist();
            obslist.add(str);
        }

        SongListUI.getItems().addAll(obslist);
    }
        //  到这结束
}
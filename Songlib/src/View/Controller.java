package View;

import Structure.Song;
import Structure.SongList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML Label SongNameShow;
    @FXML TextField SongNameAdd;
    @FXML TextField AritstAdd;
    @FXML TextField AlbumAdd;
    @FXML TextField YearAdd;
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
    public void AddTapped(ActionEvent e) {
        String songname=SongNameAdd.getText();
        String artist=AritstAdd.getText();
        String Album=AlbumAdd.getText();
        int year=0;
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
        SongList.list.add(new Song(songname,artist,Album,year));
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

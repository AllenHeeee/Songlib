package View;

import Structure.Song;
import Structure.SongList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Controller implements Initializable {    //   修改过
    ObservableList obslist= FXCollections.observableArrayList();
    @FXML Label SongNameShow;
    @FXML TextField SongNameAdd;
    @FXML TextField AritstAdd;
    @FXML TextField AlbumAdd;
    @FXML TextField YearAdd;
    @FXML Label NoticeLabel;
    @FXML ListView<String> SongListUI;
    @FXML TextField SongNameEdit;
    @FXML TextField ArtistEdit;
    @FXML TextField AlbumEdit;
    @FXML TextField YearEdit;
    @FXML Label AuthorShow;
    @FXML Label AlbumShow;
    @FXML Label YearShow;

    public void LastSongTapped(ActionEvent e) {

    }
    public void NextSongTapped(ActionEvent e) {

    }
    public void ExitTapped(ActionEvent e) {

    }
    public void EditTapped(ActionEvent e) {

    }
    public void DeleteTapped(ActionEvent e) {
        remove(SongList.list,SongNameEdit.getText(),ArtistEdit.getText());  //从这里开始
        SongListUI.getItems().clear();
        loadData();
        try{
            SongList.loadListIntoFile();
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
    public void ChooseTapped(ActionEvent e) {
        SongList.currIndex = SongListUI.getSelectionModel().getSelectedIndex();
        retrieveData(SongList.currIndex);
    }
    public void AddTapped(ActionEvent e) {
        String songname=SongNameAdd.getText();
        String artist=AritstAdd.getText();
        String Album=AlbumAdd.getText();
        int year=0;
        try {
             year=Integer.parseInt(YearAdd.getText());
        }catch (Exception ex){
            showInputError("Bad Input!");
            return;
        }

        if(songname.trim().isEmpty()||artist.trim().isEmpty()||Album.trim().isEmpty()||year<0||year>2019){
            NoticeLabel.setText("Add Failed!");
            System.out.println("Add Failed!");
            showInputError("Bad Input!");
            return;
        }
       if(!SongList.addIntoAL(new Song(songname,artist,Album,year+""))){
           System.out.println("Add Failed!");
           showInputError("Song Existed!");
           NoticeLabel.setText("");
           SongNameAdd.setText("");
           AritstAdd.setText("");
           AlbumAdd.setText("");
           YearAdd.setText("");
           NoticeLabel.setText("Add Failed!");
           return;
       }

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
        YearEdit.setText(String.valueOf(year));
        SongListUI.getSelectionModel().select(songname+"   By:"+artist);
    }
    public static void showInputError(String mess){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Add Failed");
        alert.setContentText(mess);
        alert.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            SongList.fildRead();
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
        if(SongList.list.size()!=0){
            loadData();
            SongListUI.getSelectionModel().select(0);
            if(SongList.list.get(0).getSongName()!=null) {
                SongList.currIndex=0;
                /// Edited!
                retrieveData(SongList.currIndex);
            }
        }
    }
    public void retrieveData(int index){
        SongNameEdit.setText(SongList.list.get(index).getSongName());
        SongNameShow.setText(SongList.list.get(index).getSongName());
        ArtistEdit.setText(SongList.list.get(index).getArtist());
        AuthorShow.setText("By: "+SongList.list.get(index).getArtist());
        AlbumEdit.setText(SongList.list.get(index).getAlbum());
        AlbumShow.setText("Album: "+SongList.list.get(index).getAlbum());
        YearEdit.setText(SongList.list.get(index).getYear());
        YearShow.setText("Year: "+SongList.list.get(index).getYear());
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
    public static void remove(ArrayList<Song> list, String target1, String target2){
        Iterator<Song> iter=list.iterator();
        while(iter.hasNext()){
            Song item=iter.next();
            if(item.getSongName().equals(target1) && item.getArtist().equals(target2))
                iter.remove();
        }
    }


}

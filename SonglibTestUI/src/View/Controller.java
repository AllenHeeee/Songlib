
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    ObservableList obslist= FXCollections.observableArrayList();
    @FXML Label SongNameShow;
    @FXML TextField SongNameAdd;
    @FXML TextField AritstAdd;
    @FXML TextField AlbumAdd;
    @FXML TextField YearAdd;
    @FXML TextField SongNameEdit;
    @FXML TextField ArtistEdit;
    @FXML TextField AlbumEdit;
    @FXML TextField YearEdit;
    @FXML ListView<String> SongListUI;
    @FXML Label NoticeLabel;
    @FXML Label AuthorShow;
    @FXML Label AlbumShow;
    @FXML Label YearShow;
    public void LastSongTapped(ActionEvent e) {
        if(SongList.list.size()==0){
            return;
        }
        if(SongList.currindex-1<0){
            return;
        }
        SongList.currindex--;
        retrieveData(SongList.currindex);
        SongListUI.getSelectionModel().select(SongList.currindex);
    }
    public void NextSongTapped(ActionEvent e) {
        if(SongList.list.size()==0){
            return;
        }
        if(SongList.currindex+1>=SongList.list.size()){
            return;
        }
        SongList.currindex++;
        retrieveData(SongList.currindex);
        SongListUI.getSelectionModel().select(SongList.currindex);
    }
    public void ExitTapped(ActionEvent e) {

    }
    public void EditTapped(ActionEvent e) {           //开始
        //int pos=SongListUI.getSelectionModel().getSelectedIndex();
        String Songname=SongList.list.get(SongList.currindex).getSongName();
        String Artist=SongList.list.get(SongList.currindex).getArtist();
        String Album=SongList.list.get(SongList.currindex).getAlbum();
        String Year=SongList.list.get(SongList.currindex).getYear();
        String Songname2=SongNameEdit.getText();
        String Artist2=ArtistEdit.getText();
        String Album2=AlbumEdit.getText();
        String Year2=YearEdit.getText();
        int year=0;
        try {
            year=Integer.parseInt(YearEdit.getText());
        }catch (Exception ex){
            showInputError("Bad Input");
            NoticeLabel.setText("Add Failed!");
            return;
        }
        if((!SongList.addIntoAL(new Song(Songname2,Artist2,Album2,year+""))) && (!Songname.equals(Songname2)) &&(!Artist.equals(Artist2))){
                    NoticeLabel.setText("Edit Fail! The song already exists");
                    System.out.println("Edit Failed!");
                    SongNameEdit.setText(Songname);
                    ArtistEdit.setText(Artist);
                    AlbumEdit.setText(Album);
                    YearEdit.setText(Year);
                    return;

        }
        if(Songname.equals(Songname2) && Artist.equals(Artist2))
        {
            SongList.list.get(SongList.currindex).setAlbum(Album2);
            SongList.list.get(SongList.currindex).setYear(Year2);
        }
        else
        remove(SongList.list,Songname,Artist);
        try{
            SongList.loadListIntoFile();
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
        SongListUI.getItems().clear();
        loadData();
        SongListUI.getSelectionModel().select(Songname2+"   By:"+Artist2);
        SongNameShow.setText(Songname2);
        AuthorShow.setText("By: "+Artist2);
        AlbumShow.setText("Album: "+Album2);
        YearShow.setText("Year: "+Year2);
    }                                //结束
    public void DeleteTapped(ActionEvent e) {
       SongList.currindex=SongListUI.getSelectionModel().getSelectedIndex();
       if(SongNameEdit.getText().isEmpty())       //开始
            NoticeLabel.setText("Delete Fail!");
        else
            NoticeLabel.setText("Delete Success!");// 结束
        remove(SongList.list,SongNameEdit.getText(),ArtistEdit.getText());
        SongListUI.getItems().clear();
        loadData();
        try{
            SongList.loadListIntoFile();
        }catch (Exception ex) {
            System.out.println(ex.toString());
        }
        if(SongList.currindex>=SongList.list.size())
        {
            SongList.currindex--;
        }
        if(SongList.currindex<0)
        {
            SongNameShow.setText("Song Name");
            AuthorShow.setText("Artist");
            AlbumShow.setText("Album");
            YearShow.setText("Year");
            SongNameEdit.setText("");
            ArtistEdit.setText("");
            AlbumEdit.setText("");
            YearEdit.setText("");

        }else {
            retrieveData(SongList.currindex);
            SongListUI.getSelectionModel().select(SongList.currindex);
        }
    }
    public void ChooseTapped(ActionEvent e) {
        SongList.currindex = SongListUI.getSelectionModel().getSelectedIndex();
        retrieveData(SongList.currindex);
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
            showInputError("Bad Input");
            NoticeLabel.setText("Add Failed!");
            return;
        }

        if(songname.trim().isEmpty()||artist.trim().isEmpty()||Album.trim().isEmpty()||year<0||year>2019){
            showInputError("Bad Input");
            NoticeLabel.setText("Add Failed!");
            System.out.println("Add Failed!");
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
        SongListUI.getItems().clear();
        loadData();
        SongNameEdit.setText(songname);
        ArtistEdit.setText(artist);
        AlbumEdit.setText(Album);
        YearEdit.setText(yearStr);
        SongNameShow.setText(songname);
        AuthorShow.setText("By: "+artist);
        AlbumShow.setText("Album: "+Album);
        YearShow.setText("Year: "+yearStr);
        SongListUI.getSelectionModel().select(songname+"   By:"+artist);
    }
    public static void showInputError(String mess){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Add Failed");
        alert.setContentText(mess);
        alert.showAndWait();
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
            SongList.currindex=0;
            retrieveData(SongList.currindex);
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
    public static void remove(ArrayList<Song> list, String target1, String target2){
        Iterator<Song> iter=list.iterator();
        while(iter.hasNext()){
            Song item=iter.next();
            if(item.getSongName().equals(target1) && item.getArtist().equals(target2))
                iter.remove();
        }
    }

}
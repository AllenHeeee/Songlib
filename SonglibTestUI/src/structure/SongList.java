package structure;

import javafx.scene.control.Alert;
import structure.Song;

import java.io.*;
import java.util.ArrayList;

public class SongList {
    public static ArrayList<Song> list = new ArrayList<Song>();
    public static String deleteAssignChar(String str,int on,char c) {
        int count = 0;
        for(int i = 0;i < str.length();i++) {
            if(str.charAt(i) == c)
                count++;
            if(count == on) {
                str = str.substring(0,i) + str.substring(i+1);
            }
        }
        return str;
    }
  //  public static ArrayList<Song> listforRe = new ArrayList<Song>();   //
    public static void loadListIntoFile() throws IOException {
        String fileName = "../SonglibTestUI/src/structure/database.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        String str = "";
        for(int i=0;i<list.size();i++){
            str='@'+list.get(i).getSongName()+'@'+list.get(i).getArtist()+'@'+ list.get(i).getAlbum()+'@'+list.get(i).getYear()+"\n";
            writer.write(str);
        }
        writer.close();
    }
    public static void fildRead() throws Exception{
        File file=new File("../SonglibTestUI/src/structure/database.txt");
        FileReader reader=new FileReader(file);
        BufferedReader bReader =new BufferedReader(reader);
        StringBuilder sb=new StringBuilder();
        String s="";
        String c[];
        while((s=bReader.readLine())!=null){
            c=s.split("@");
            c[1]=deleteAssignChar(c[1],1,'@');
            c[2]=deleteAssignChar(c[2],1,'@');
            c[3]=deleteAssignChar(c[3],1,'@');
            c[4]=deleteAssignChar(c[4],1,'@');
            list.add(new Song(c[1],c[2],c[3],c[4]));
        }
        bReader.close();
    }

    public static void showInputError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Add Failed");
        alert.setContentText("Bad Input!");
        alert.showAndWait();
    }

}
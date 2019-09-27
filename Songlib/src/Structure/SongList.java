package Structure;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SongList {
    public static ArrayList<Song> list = new ArrayList<Song>();

    public static void loadListIntoFile() throws IOException {
        String fileName = "../Songlib/src/Structure/database.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        String str = "";
        for(int i=0;i<list.size();i++){
            str+="@SongName: "+list.get(i).getSongName()+"\t"+"@Artist: "+list.get(i).getArtist()+"\t"+"@Album: "+
                    list.get(i).getAlbum()+"\t"+"@Year: "+list.get(i).getYear()+"\n";
        }
        writer.write(str);
        writer.close();
    }

}

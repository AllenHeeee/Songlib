package Structure;

public class Song {
    String songName;
    String artist;
    String album;
    int year;
    public Song(String songName,String artist,String album,int year){
        this.songName=songName;
        this.artist=artist;
        this.album=album;
        this.year=year;
    }
    public String getSongName(){
        return this.songName;
    }
    public String getArtist(){
        return this.artist;
    }
    public String getAlbum(){
        return this.album;
    }
    public int getYear(){
        return this.year;
    }
    public void setSongName(String newName){
        this.songName=newName;
    }
    public void setArtist(String newArtist){
        this.artist=newArtist;
    }
    public void setAlbum(String newAlbun){
        this.album=newAlbun;
    }
    public void setYear(int newYear){
        this.year=newYear;
    }
    public boolean equals(Object another){
        if(this == another)
            return true;

        if(another == null || another.getClass()!= this.getClass())
            return false;

        Song ano = (Song) another;

        return (ano.songName.equals(this.songName)  && ano.artist.equals(this.artist)&& ano.album.equals(this.album)&& ano.year == this.year);

    }
}

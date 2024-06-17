package songs.models;
import javax.persistence.*;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String artist;
    private String description;
    private String linkSong;

    public Song() {}

    public Song(int id, String name, String artist, String description, String file) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.description = description;
        this.linkSong = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String file) {
        this.linkSong = file;
    }
}

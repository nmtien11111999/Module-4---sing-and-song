package songs.services;

import songs.models.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();

    void save(Song song);

    void delete(int id);

    Song findById(int id);
}

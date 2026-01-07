package DesignPatterns.Iterator;

import java.util.List;

public class SimplePlaylistIterator implements PlaylistIterator {

    public Playlist playlist;
    private int index;

    public SimplePlaylistIterator(Playlist playlist) {
        this.playlist = playlist;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < playlist.songs.size()-1;
    }

    @Override
    public String getNext() {
        return playlist.songs.get(index++);
    }
}

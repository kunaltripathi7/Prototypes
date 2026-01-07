package DesignPatterns.Iterator;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    List<String> songs = new ArrayList<String>();

    public void addSong(String song) {
        songs.add(song);
    }

    public PlaylistIterator playPlaylist(String type) {
//        if (type == "shuffled") {
//            // shuffled playlist logic.
//        }
//        else if (type == "simple") {
//            //simple playlist logic.
//        }
//        else {
//
//        }

        // not scalable at all -> not flexible // violation open closed principle, tightly coupled
        // solution -> if/else -> zoom out create interface -> accept interface and if/else logic in the implemting classes

        switch (type) {
            case "Shuffle":
                return new SimplePlaylistIterator(this);
            default:
                return null;
        }


    }
}

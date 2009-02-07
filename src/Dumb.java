import org.jmpc.service.SimpleMPDPlayer;
import org.jmpc.model.Song;

public class Dumb {

    public static void main(String args[]) {
        SimpleMPDPlayer mpd = new SimpleMPDPlayer("localhost", 6600);
        Song currentSong = mpd.getCurrentSong();
        System.out.println("> Currently playing: " + currentSong.getFile() + ". Length: " + currentSong.getTime());
    }

    private static void sleep(long mili) {
        try {
            Thread.sleep(mili);
        } catch(Exception e) {
            //who cares
        }
    }

}

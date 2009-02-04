import org.jmpc.service.SimpleMPDPlayer;
        
public class Dumb {

    public static void main(String args[]) {
        SimpleMPDPlayer mpd = new SimpleMPDPlayer("localhost", 6600);
        mpd.pause();
        sleep(5000);
        mpd.play();
        sleep(40000);
        mpd.pause();
        mpd.play();
    }

    private static void sleep(long mili) {
        try {
            Thread.sleep(mili);
        } catch(Exception e) {
            //who cares
        }
    }

}

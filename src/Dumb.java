import org.jmpc.socket.SimpleMPDSocket;
        
public class Dumb {

    public static void main(String args[]) {
        SimpleMPDSocket mpd = new SimpleMPDSocket();
        mpd.connect("localhost", 6600);
        mpd.submit("pause");
        System.out.println(mpd.read());

    }

}

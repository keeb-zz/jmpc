package org.jmpc.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jmpc.socket.SimpleMPDSocket;
import org.jmpc.model.Song;

public class SimpleMPDPlayer implements MPDPlayer {

    private final Log logger = LogFactory.getLog(getClass());
    private SimpleMPDSocket mpd;

    public SimpleMPDPlayer(String host, int port) {
        mpd = new SimpleMPDSocket(host, port);
    }
    
    public void play() {
        mpd.submit("play");
    }

    public void previous() {
        mpd.submit("previous");
    }

    public void next() {
        mpd.submit("next");
    }

    public void pause() {
        mpd.submit("pause");
    }


    public Song getCurrentSong() {
        String response;
        response = mpd.submit("currentsong");
        return new Song(response);
    }

}

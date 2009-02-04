package org.jmpc.socket;

public interface MPDSocket {
    public boolean connect(String host, int port);
    public String submit(String command);
}

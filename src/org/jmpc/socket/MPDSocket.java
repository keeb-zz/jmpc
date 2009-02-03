package org.jmpc.socket;

public interface MPDSocket {
    public void connect(String host, int port);
    public void submit(String command);
    public String read();
}

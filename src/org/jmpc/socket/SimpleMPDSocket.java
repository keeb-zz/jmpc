package org.jmpc.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;


import java.net.Socket;
import java.net.UnknownHostException;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SimpleMPDSocket implements MPDSocket {

    private final Log logger = LogFactory.getLog(getClass());
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    @Override
    public String read() {
        String s;
        StringBuilder sb = new StringBuilder();

        try {
            while((s = in.readLine()) != null) {
                sb.append(s);
                if (sb.indexOf("OK")== 0) {
                    return sb.toString();
                }
            }
        } catch (IOException ioe) {
            logger.error("Couldnt read the socket for some reason");
        }

        return sb.toString();
    }

    @Override
    public void submit(String command) {
        logger.info("Submitting the following command to MPD: " + command);

        try {
            if (!command.contains("\n"))
                command += "\n";
            out.write(command);
            out.flush();
        } catch (IOException ioe) {
            logger.error("Socket not open.");
        }
    }

    @Override
    public void connect(String host, int port) {
        logger.info("Connecting to " + host + " on " + port);
        try {
            Socket socket = new Socket(host, port);

            in = new BufferedReader(new InputStreamReader(
                    new BufferedInputStream(socket.getInputStream())));

            out = new BufferedWriter(new OutputStreamWriter(
                    new BufferedOutputStream(socket.getOutputStream())));

        } catch (UnknownHostException uhe) {
            logger.error("Could not find host");
        } catch (IOException ioe) {
            logger.error("Could not open socket");
        }
    }

}

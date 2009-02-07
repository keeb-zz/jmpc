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

    private String host;
    private int port;

    public SimpleMPDSocket(String host, int port) {
        this.host = host;
        this.port = port;
        connect(host,port);
    }

    private String read() {
        String s;
        StringBuilder sb = new StringBuilder();

        try {
            while((s = in.readLine()) != null) {
                sb.append(s + "\n");
                if (sb.toString().contains("OK")) {
                    return sb.toString();
                }
            }
        } catch (IOException ioe) {
            logger.error("Couldnt read the socket for some reason");
        }
        logger.info("returning outside of the loop");
        return sb.toString();
    }

    @Override
    public String submit(String command) {
        logger.info("Submitting the following command to MPD: " + command);
        try {
            if (!command.contains("\n")) command += "\n";
            out.write(command);
            out.flush();
            return read();
        } catch (IOException ioe) {
            logger.error("Socket not open. Reconnecting and resubmitting.");
            try {
                if (connect()) {
                    logger.debug("Resubmitting command:" + command);
                    submit(command);
                } else {
                    logger.error("Could not reconnect");
                }
            } catch (UnknownHostException e) {
                logger.error("wtf");
            };
        } 
        return "OK";
    }

    @Override
    public boolean connect(String host, int port) {
        logger.info("Connecting to " + host + " on " + port);
        try {
            socket = new Socket(host, port);

            in = new BufferedReader(new InputStreamReader(
                    new BufferedInputStream(socket.getInputStream())));

            out = new BufferedWriter(new OutputStreamWriter(
                    new BufferedOutputStream(socket.getOutputStream())));

            logger.info("Everything is set up");

        } catch (UnknownHostException uhe) {
            logger.error("Could not find host");
        } catch (IOException ioe) {
            logger.error("Could not open socket");
        }

        return checkCommandStatus();
    }

    public boolean connect() throws UnknownHostException {
        if (host == null || port == 0) {
            logger.error("Connect called without host/port set!");
            throw new UnknownHostException("Host and port were not set!");
        }

        return connect(host, port);
    }


    private boolean checkCommandStatus() {
        return read().contains("OK");
    }

}

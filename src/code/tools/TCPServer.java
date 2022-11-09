package code.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import code.Canal;
import code.models.Message;

public class TCPServer {

    private ServerSocket servSocket;
    private Canal myCanal;

    public TCPServer(Canal canal) throws IOException {
        this.myCanal = canal;
        this.servSocket = new ServerSocket(this.myCanal.getPortTCP());
    }

    public Message TCPRecv() throws IOException {
        Socket link = servSocket.accept();
        InputStream InStream = link.getInputStream();

    }
}
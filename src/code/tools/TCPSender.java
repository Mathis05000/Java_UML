package code.tools;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import code.models.Message;

public class TCPSender {
    
    private Socket link;

    public TCPSender(InetAddress addr, int port) throws IOException {
        this.link = new Socket(addr, port);
    }

    public void send(Message message) throws IOException {
        ObjectOutputStream outStream = new ObjectOutputStream(link.getOutputStream());

        outStream.writeObject(message);

        outStream.close();
    }
}

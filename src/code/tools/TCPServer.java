package code.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import code.Canal;
import code.models.Message;

public class TCPServer extends Thread {
    
    private ServerSocket servSocket;
    private Canal canal;

    public TCPServer(Canal canal) throws IOException {
        this.servSocket = new ServerSocket(1234);
        this.canal = canal;
    }

    public Message TCPReceive() throws IOException, ClassNotFoundException {
        Socket link = servSocket.accept();
        ObjectInputStream inStream = new ObjectInputStream(link.getInputStream());

        Message message = (Message) inStream.readObject();
        inStream.close();

        return message;
    }

    public void run() {
        try {
            this.canal.messageHandler(this.TCPReceive());
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

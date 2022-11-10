package code.tools;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import code.Canal;
import code.models.Message;

public class UDPServer extends Thread {

    private DatagramSocket dgramSocket;
    private byte[] buffer;
    private int sizeBuf = 500;
    private Canal myCanal;

    public UDPServer(Canal myCanal) throws SocketException {
        System.out.println("Server");
        this.myCanal = myCanal;
        this.dgramSocket = new DatagramSocket(this.myCanal.getPortUDP());
        this.buffer = new byte[this.sizeBuf];
    }

    public Message UDPRecv() throws IOException, ClassNotFoundException {

        DatagramPacket inPacket = new DatagramPacket(this.buffer, this.buffer.length);
        dgramSocket.receive(inPacket);

        InetAddress address = inPacket.getAddress();

        ByteArrayInputStream byteStream = new ByteArrayInputStream(this.buffer);
        ObjectInputStream inStream = new ObjectInputStream(new BufferedInputStream(byteStream));
        Message message = (Message) inStream.readObject();
        inStream.close();

        message.setSource(address);

        return (message);
    }

    public void run() {
        while (true) {
            try {
                this.myCanal.messageHandler(UDPRecv());
            } catch (IOException | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

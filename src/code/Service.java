package code;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

import code.models.Agent;
import code.models.Message;
import code.models.MessageConnect;
import code.models.MessageConnectAck;
import code.models.MessageDisconnect;
import code.models.MessageSession;
import code.models.Session;

public class Service {

    private Config config;
    private Canal canal;

    public Service() throws UnknownHostException, SocketException {
        this.config = Config.getConfig();
        this.canal = new Canal();
    }

    public void processMessageConnect(MessageConnect message) throws IOException {

        // Ckeck pseudo
        if (this.config.checkPseudo(message.getData())) {
            this.config.addAgent(new Agent(message.getData(), message.getSource()));
            this.canal.sendConnectAck(this.config.getPseudo(), true, message.getSource());
        } else {
            this.canal.sendConnectAck(this.config.getPseudo(), false, message.getSource());
        }
    }

    public void processMessageConnectAck(MessageConnectAck message) throws IOException {

        this.config.addAgent(new Agent(message.getData(), message.getSource()));

    }

    public void processMessageSession(MessageSession message) {
        this.config.addSession(Session.createByMessage(message, this.config));
    }

    public void processMessageDisconnect(MessageDisconnect message) {
        this.config.delAgent(this.config.getAgentByAddress(message.getSource().getHostAddress()));
    }
}

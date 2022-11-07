package code;

import java.net.InetAddress;
import java.net.SocketException;

import code.models.Message;

public class Service {
    
    private Service() {}

    private static class ServiceHolder {
		private final static Service instance = new Service();
	}

	public static Service getService() {
		return ServiceHolder.instance;
	}

    public void processMessageConnect(Message message, InetAddress address) {
        
    }

    public void processMessageConnectAck(Message message, InetAddress address) {
        
    }
}

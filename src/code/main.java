package code;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import code.models.Agent;
import code.models.Session;

public class main {

	public static void main(String[] args) throws IOException {
		
		Service service = new Service();

		System.out.println("enter your pseudo : ");

		Scanner sc = new Scanner(System.in);

		String pseudo = sc.nextLine();

		service.processSendConnect(pseudo);

		
		Session session = new Session(service.getConfig().getAgents());

		service.processSendSession(session);

	}

}

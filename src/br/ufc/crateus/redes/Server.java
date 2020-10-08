package br.ufc.crateus.redes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private int port;

	public Server(int port) {
		this.port = port;
	}
	
	@SuppressWarnings("resource")
	public void listen() throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println(String.format("Server listen on http://localhost:%d", port));
		while(true) {
			final Socket clientSocket = serverSocket.accept();
			SocketHandler socketHandler = new SocketHandler(clientSocket);
			socketHandler.start();
		}
	}
	
}

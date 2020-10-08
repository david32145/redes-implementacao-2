package br.ufc.crateus.redes;

import java.io.IOException;

public class Application {
	public static void main(String[] args) throws IOException {
		Server server = new Server(5000);
		server.listen();
	}
}

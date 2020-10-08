package br.ufc.crateus.redes;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketHandler extends Thread {
	private Socket socket;

	public SocketHandler(Socket socket) {
		this.socket = socket;
	}

	public void handler() throws IOException {
		Request request = new Request(socket.getInputStream());
		Response response = null;
		
		if(request.getPath().equals("/") && request.getMethod() == Method.GET) {
			response = new Response(StatusCode.OK);
			response.setContentType("text/html");
			if(request.getBody() != null && !request.getBody().isBlank()) {
				response.setBody("<p> Bem vindo: " + request.getBody() +"</p>");				
			} else {
				response.setBody("<p>Bem vindo pelo browser</p>");
			}
		} else {
			response = new Response(StatusCode.NOT_FOUND);
		}

		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(response.getMessage().getBytes());
		outputStream.flush();
		socket.close();
	}

	@Override
	public void run() {
		try {
			handler();
		} catch (Exception exception) {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}
}

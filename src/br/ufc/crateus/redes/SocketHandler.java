package br.ufc.crateus.redes;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class SocketHandler extends Thread {
	private Socket socket;

	public SocketHandler(Socket socket) {
		this.socket = socket;
	}

	public void handler() throws IOException {
		Request request = new Request(socket.getInputStream());
		Response response = null;
		try {
			if (request.isPostFor("/login")) {
				Map<String, String> params = request.getBodyParams();
				String result = UserData
							.getInstance()
							.login(params.get("email"), params.get("password"));
				if (result.startsWith("Bem vindo")) {
					response = new Response(StatusCode.OK);
				} else {
					response = new Response(StatusCode.BAD_REQUEST);
				}
				if(request.isBrowserRequest()) {
					response.setBody(HtmlResponse.getResponse(result));
					response.setContentType("text/html");
				} else {
					response.setBody(String.format("{\"message\": \"%s\"}", result));
					response.setContentType("application/json");
				}
			} else if (request.isGetFor("/login")) {
				response = new Response(StatusCode.OK);
				response.setContentType("text/html");
				response.setBody(HtmlResponse.LOGIN_HTML);
			} else {
				if (request.isBrowserRequest()) {
					response = new Response(StatusCode.OK);
					response.setContentType("text/html");
					response.setBody(HtmlResponse.HTML_NOT_FOUND);
				} else {
					response = new Response(StatusCode.NOT_FOUND);
					response.setContentType("application/json");
				}
			}
		} catch (Throwable e) {
			if (request.isBrowserRequest()) {
				response = new Response(StatusCode.OK);
				response.setContentType("text/html");
				response.setBody(HtmlResponse.HTML_INTERNAL_ERROR);
			} else {
				response = new Response(StatusCode.INTERNAL_ERROR);
				response.setContentType("application/json");
			}
		}

		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(response.getMessage().getBytes("UTF-8"));
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

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
		if(request.getPath().equals("/login") && request.getMethod() == Method.POST) {
			Map<String, String> params =  request.getBodyParams();
			response = new Response(StatusCode.OK);
			response.setContentType("text/html");
			response.setBody(HtmlResponse.getResponse(UserData.getInstance().login(params.get("email"), params.get("password"))));
		}
		else if (request.getPath().equals("/login") && request.getMethod() == Method.GET) {
			response = new Response(StatusCode.OK);
			response.setContentType("text/html");
			response.setBody(HtmlResponse.LOGIN_HTML);
		} else {
			if(request.getAccept() != null && request.getAccept().contains("text/html")) {
				response = new Response(StatusCode.OK);
				response.setContentType("text/html");
				response.setBody(HtmlResponse.HTML_NOT_FOUND);				
			}
			else {
				response = new Response(StatusCode.NOT_FOUND);
				response.setContentType("application/json");
			}
		}
		}catch (Throwable e) {
			if(request.getAccept() != null && request.getAccept().contains("text/html")) {
				response = new Response(StatusCode.OK);
				response.setContentType("text/html");
				response.setBody(HtmlResponse.HTML_INTERNAL_ERROR);				
			}
			else {
				response = new Response(StatusCode.INTERNAL_ERROR);
				response.setContentType("application/json");
			}
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

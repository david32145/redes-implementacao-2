package br.ufc.crateus.redes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Response {
	private StatusCode statusCode;
	private Map<String, String> headers;
	private String body;

	public Response(StatusCode statusCode) {
		this.statusCode = statusCode;
		headers = new HashMap<String, String>();
		body = "";
	}

	public void addHeader(String key, String value) {
		headers.put(key, value);
	}
	
	public void setContentType(String value) {
		headers.put("Content-Type", value);
	}

	public void setBody(String body) {
		this.body = body;
		headers.put("Content-Length", body.length() + "");
	}

	public String getMessage() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
			.append("HTTP/1.1 ")
			.append(statusCode.getCode())
			.append(" ")
			.append(statusCode.getName())
			.append("\r\n");
		
		headers.forEach(new BiConsumer<String, String>() {
			@Override
			public void accept(String key, String value) {
				stringBuilder.append(key)
				.append(": ")
				.append(value)
				.append("\r\n");
			}
		});
		if(body != null && !body.isEmpty()) {
			stringBuilder.append("\r\n");
			stringBuilder.append(body);
			stringBuilder.append("\r\n\r\n");
		}
		return stringBuilder.toString();
	}
}

package br.ufc.crateus.redes;

import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;

public class Request {
	private InputStream inputStream;
	private Method method;
	private String path;
	private boolean httpV1;
	private String body;
	private Map<String, String> headers;

	public Request(InputStream inputStream) throws IOException {
		this.inputStream = inputStream;
		this.headers = new HashMap<String, String>();
		this.body = "";
		this.init();
	}
	
	private void readMessage() throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line;
		List<String> lines = new ArrayList<String>();
		while (!(line = bufferedReader.readLine()).isBlank()) {
			lines.add(line);
		}
		readStartLine(lines.get(0));
		readHeaders(lines);
		long contentLength = getContentLength();
		while(contentLength-- != 0) {
			body += ((char) bufferedReader.read());
		}
	}
	
	public long getContentLength() {
		return Long.parseLong(headers.getOrDefault("Content-Length", "0"));
	}
	
	private void readStartLine(String startLine) {
		String split[] = startLine.split(" ");
		this.method = Method.valueOf(split[0].toUpperCase());
		this.path = split[1];
		this.httpV1 = split[2].equals("HTTP/1.1");
	}
	
	private int readHeaders(List<String> requestHeaders) {
		for(int i = 1; i < requestHeaders.size(); i++) {
			String header = requestHeaders.get(i);
			if(header == null || header.length() == 0) return i + 1;
			String split[] = header.split(": ");
			headers.put(split[0], split[1]);
		}
		return requestHeaders.size();
	}
	
	public Map<String, String> getBodyParams() throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		for(String s : body.split("&")) {
			String param[] = s.split("=");
			params.put(param[0], URLDecoder.decode(param[1], "UTF-8"));
		}
		return params;
	}
	
	private void init() throws IOException {
		readMessage();
	}
	
	public Method getMethod() {
		return method;
	}
	
	public boolean isHttpV1() {
		return httpV1;
	}
	
	public String header(String key) {
		return headers.get(key);
	}
	
	public String getPath() {
		return path;
	}
	
	public String getBody() {
		return body;
	}
	
	public String getAccept() {
		return this.headers.getOrDefault("Accept", null);
	}
}

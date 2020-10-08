package br.ufc.crateus.redes;

public enum StatusCode {
	OK(200, "OK"),
	NOT_FOUND(404, "Not Found");
	
	private int code;
	private String name;
	
	private StatusCode(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
}

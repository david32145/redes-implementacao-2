package br.ufc.crateus.redes;

public enum StatusCode {
	OK(200, "OK"),
	BAD_REQUEST(400, "Bad Request"),
	NOT_FOUND(404, "Not Found"),
	INTERNAL_ERROR(500, "Internal Server Error");
	
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

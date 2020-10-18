package br.ufc.crateus.redes;

import java.util.HashMap;
import java.util.Map;

public class UserData {
	private Map<String, String> usersMap;
	
	private static UserData instance;
	
	public static UserData getInstance() {
		if(instance == null) {
			instance = new UserData();
		}
		return instance;
	}

	public UserData() {
		this.usersMap = new HashMap<>();
		this.usersMap.put("david@gmail.com", "12345");
		this.usersMap.put("filipe@ufc.com", "osi1990");
		this.usersMap.put("henry@gmail.com", "123456");
	}
	
	public String login(String email, String password) {
		if(this.usersMap.containsKey(email)) {
			if(this.usersMap.get(email).equals(password)) {
				return String.format("Bem vindo %s", email);
			}
			return "Senha incorreta";
		}
		return "Email inexistente";
	}
}

package br.ufc.crateus.redes;

public class HtmlResponse {
	public final static String LOGIN_HTML = "<!DOCTYPE html>\n" + 
			"<html lang=\"en\">\n" + 
			"<head>\n" + 
			"  <meta charset=\"UTF-8\">\n" + 
			"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + 
			"  <title>Login</title>\n" + 
			"</head>\n" + 
			"<body>\n" + 
			"  <form action=\"/login\" method=\"POST\">\n" + 
			"    <label for=\"email\">Email</label>\n" + 
			"    <input type=\"email\" name=\"email\" id=\"email\"><br>\n" + 
			"    <label for=\"password\">Password</label>\n" + 
			"    <input type=\"password\" name=\"password\" id=\"password\"><br>\n" + 
			"    <button type=\"submit\">Entrar</button>\n" + 
			"  </form>\n" + 
			"</body>\n" + 
			"</html>";
	
	public final static String HTML_NOT_FOUND = "<!DOCTYPE html>\n" + 
			"<html lang=\"en\">\n" + 
			"<head>\n" + 
			"  <meta charset=\"UTF-8\">\n" + 
			"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + 
			"  <title>Not found</title>\n" + 
			"</head>\n" + 
			"<body>\n" + 
			"  <h1>Essa página não existe</h1>\n" + 
			"</body>\n" + 
			"</html>";
	
	public final static String HTML_INTERNAL_ERROR = "<!DOCTYPE html>\n" + 
			"<html lang=\"en\">\n" + 
			"<head>\n" + 
			"  <meta charset=\"UTF-8\">\n" + 
			"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + 
			"  <title>Error</title>\n" + 
			"</head>\n" + 
			"<body>\n" + 
			"  <h1>Ocorreu um error interno</h1>\n" + 
			"</body>\n" + 
			"</html>";
	
	public final static String getResponse(String result) {
		return "<!DOCTYPE html>\n" + 
				"<html lang=\"en\">\n" + 
				"<head>\n" + 
				"  <meta charset=\"UTF-8\">\n" + 
				"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + 
				"  <title>Login</title>\n" + 
				"</head>\n" + 
				"<body>\n" + 
				"  <h1>"+result+"</h1>\n" + 
				"</body>\n" + 
				"</html>";
	}
}

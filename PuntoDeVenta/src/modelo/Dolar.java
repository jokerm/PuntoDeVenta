package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Dolar {
	public static void main(String[] args) {
		try {
			System.out.println(new Dolar().getDolar());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getDolar() throws IOException {
		String dolar = "";
		//URL url = new URL("https://graph.facebook.com/search?q=java&type=post");
		URL url = new URL("http://puntodeventa.cu.cc/dolar.php");
		InputStream is = url.openStream();
		JsonReader rdr = Json.createReader(is);
		JsonObject obj = rdr.readObject();
/*     JsonArray results = obj.getJsonArray("data");
 7     for (JsonObject result : results.getValuesAs(JsonObject.class)) {
 8         System.out.print(result.getJsonObject("from").getString("name"));
 9         System.out.print(": ");
10         System.out.println(result.getString("message", ""));
11         System.out.println("-----------");
12     }
 */
		dolar = obj.getString("dolar");
		return dolar;
	}
}

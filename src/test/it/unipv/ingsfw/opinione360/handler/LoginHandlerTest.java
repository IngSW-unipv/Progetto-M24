package test.it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.UtenteDAO;
import it.unipv.ingsfw.opinione360.server.ServerSingleton;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoginHandlerTest {
    ServerSingleton server;
    HttpClient client;
    Gson gson;
    UtenteDAO utenteDao;
    @Before
    public void init(){
        utenteDao = new UtenteDAO();
    	utenteDao.reset();
        server = ServerSingleton.getIstance();
        client = HttpClient.newHttpClient();
        gson = new Gson();
    }
    @Test
    public void handleTestKO() throws IOException, InterruptedException {
        server.startServer();
        Utente u = new Utente();
        HttpRequest r = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Login")).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(u))).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(404, response.statusCode());
    }
    @Test
    public void handleTestKO2() throws IOException, InterruptedException {
        HttpRequest r = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Login")).POST(HttpRequest.BodyPublishers.ofString("   ")).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
    }

     @Test
    public void handleTestOK() throws IOException, InterruptedException {
        Utente u = new Utente("Tizio","pw","tizio@opinione360.it", "TC1");
        utenteDao.insertUtente(u);
        HttpRequest r = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Login")).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(u))).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }



}

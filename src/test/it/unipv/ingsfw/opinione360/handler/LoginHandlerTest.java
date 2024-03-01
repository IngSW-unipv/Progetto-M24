package test.it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import it.unipv.ingsfw.opinione360.exception.UserNotFoundException;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.UtenteDAO;
import it.unipv.ingsfw.opinione360.server.ServerSingleton;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    URI uri = URI.create("http://127.0.0.1:60000/Login");
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
        Utente fakeUser = new Utente();
        HttpRequest richiestaFakeUser = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(fakeUser))).build();
        HttpResponse<String> response = client.send(richiestaFakeUser, HttpResponse.BodyHandlers.ofString());
        assertEquals(404, response.statusCode());
        assertEquals("Impossibile trovare l'utente specificato.", response.body());
    }
    @Test
    public void handleTestKO2() throws IOException, InterruptedException {
        HttpRequest richiestaNoUser = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString("   ")).build();
        HttpResponse<String> response = client.send(richiestaNoUser, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
        assertEquals("Dati mancanti.", response.body());
    }
    @Test
    public void handleTestOK() throws IOException, InterruptedException {
        Utente user = new Utente("Test","pw1","test@opinione360.it", "TEST");
        utenteDao.insertUtente(user);
        HttpRequest richiestaCorretta = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user))).build();
        HttpResponse<String> response = client.send(richiestaCorretta, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());

    }

}

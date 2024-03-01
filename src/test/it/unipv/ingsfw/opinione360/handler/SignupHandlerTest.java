package test.it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.UtenteDAO;
import it.unipv.ingsfw.opinione360.server.ServerSingleton;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SignupHandlerTest {
    ServerSingleton server;
    HttpClient client;
    Gson gson;
    URI uri = URI.create("http://127.0.0.1:60000/Signup");
    @Before
    public void init(){
        UtenteDAO utenteDao = new UtenteDAO();
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
        assertEquals(406, response.statusCode());
        assertEquals("Impossibile registrarsi.", response.body());
    }
    @Test
    public void handleTestKO2() throws IOException, InterruptedException {
        HttpRequest richiestaNoUser = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString("   ")).build();
        HttpResponse<String> response = client.send(richiestaNoUser, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
        assertEquals("Dati mancanti.", response.body());
    }
    @Test
    public void handleTestKO3() throws IOException, InterruptedException {
        Utente user = new Utente(UUID.randomUUID(), "Due", "password2", "due@opinione360.it", "U2");
        Utente userCopia = new Utente(user.getId(), "Due", "password2", "due@opinione360.it", "U2");
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user))).build();
        HttpRequest richiestaCopia= HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(userCopia))).build();
        HttpResponse<String> response = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> responseCopia = client.send(richiestaCopia, HttpResponse.BodyHandlers.ofString());
        assertEquals(406, responseCopia.statusCode());
        assertEquals("Impossibile registrarsi.", responseCopia.body());
    }
    @Test
    public void handleTestOK() throws IOException, InterruptedException {
        Utente user = new Utente("Test", "pass");
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user))).build();
        HttpResponse<String> response = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
    }
    @Test
    public void handleTestOK2() throws IOException, InterruptedException {
        Utente user = new Utente("Test", "pass", "test@opinione360.it", "U1");
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user))).build();
        HttpResponse<String> response = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
    }
    @Test
    public void handleTestOK3() throws IOException, InterruptedException {
        Utente user = new Utente(UUID.randomUUID(), "Test", "pass", "test@opinione360.it", "U1");
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user))).build();
        HttpResponse<String> response = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
    }
}
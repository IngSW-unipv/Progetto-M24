package test.it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.UtenteDAO;
import it.unipv.ingsfw.opinione360.server.ServerSingleton;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class SignupHandlerTest {
    ServerSingleton server;
    HttpClient client;
    Gson gson;
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
        Utente user = new Utente();
        HttpRequest r = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Signup")).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user))).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(500, response.statusCode());
    }
    @Test
    public void handleTestKO2() throws IOException, InterruptedException {
        HttpRequest r = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Login")).POST(HttpRequest.BodyPublishers.ofString("   ")).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
    }
    @Test
    public void handleTestKO3() throws IOException, InterruptedException {
        Utente user = new Utente(UUID.randomUUID(), "Uno", "password1", "uno@opinione360.it", "U1");
        Utente user_copia = new Utente(user.getId(), "Uno", "password1", "uno@opinione360.it", "U1");
        HttpRequest r = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Signup")).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user))).build();
        HttpRequest r_copia= HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Signup")).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user_copia))).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response_copia = client.send(r_copia, HttpResponse.BodyHandlers.ofString());
        assertEquals(500, response_copia.statusCode());
    }
    @Test
    public void handleTestOK() throws IOException, InterruptedException {
        Utente user = new Utente("Uno", "password1");
        HttpRequest r = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Signup")).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user))).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }
    @Test
    public void handleTestOK2() throws IOException, InterruptedException {
        Utente user = new Utente("Uno", "password1", "uno@opinione360.it", "U1");
        HttpRequest r = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Signup")).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user))).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }
    @Test
    public void handleTestOK3() throws IOException, InterruptedException {
        Utente user = new Utente(UUID.randomUUID(), "Uno", "password1", "uno@opinione360.it", "U1");
        HttpRequest r = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Signup")).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user))).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }
}
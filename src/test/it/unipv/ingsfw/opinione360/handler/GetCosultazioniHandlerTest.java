package test.it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import it.unipv.ingsfw.opinione360.model.Amministratore;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.AmministratoreDAO;
import it.unipv.ingsfw.opinione360.server.ServerSingleton;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetCosultazioniHandlerTest {
    ServerSingleton server;
    HttpClient client;
    Gson gson;
    URI uri = URI.create("http://127.0.0.1:60000/Consultazioni_lista");
    @Before
    public void init(){
        server = ServerSingleton.getIstance();
        client = HttpClient.newHttpClient();
        gson = new Gson();
    }
    @Test
    public void handleTestOK() throws IOException, InterruptedException {
        server.startServer();
        HttpRequest richiesta= HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }

    @Test
    public void handleTestOK2() throws IOException, InterruptedException {
        HttpRequest richiesta= HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
        assertNotNull( response.body());
    }
}
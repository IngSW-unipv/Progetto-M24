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

public class GetUtentiHandlerTest {
    ServerSingleton server;
    HttpClient client;
    Gson gson;
    AmministratoreDAO adminDao;
    @Before
    public void init(){
        adminDao = new AmministratoreDAO();
    	adminDao.reset();
        server = ServerSingleton.getIstance();
        client = HttpClient.newHttpClient();
        gson = new Gson();
    }
    @Test
    public void handleTestKO() throws IOException, InterruptedException {
        server.startServer();
        Utente u = new Utente();
        HttpRequest richiesta_admininesistente = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/GetUtenti?"+ u.getId())).GET().build();
        HttpResponse<String> response = client.send(richiesta_admininesistente, HttpResponse.BodyHandlers.ofString());
        assertEquals(404, response.statusCode());
    }
    @Test
    public void handleTestKO2() throws IOException, InterruptedException {
        HttpRequest richiesta_senzaadmin = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/GetUtenti")).GET().build();
        HttpResponse<String> response = client.send(richiesta_senzaadmin, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
    }
    @Test
    public void handleTestKO3() throws IOException, InterruptedException {
        HttpRequest richiesta_casualstring = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/GetUtenti?acaso")).GET().build();
        HttpResponse<String> response = client.send(richiesta_casualstring, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
    }
    @Test
    public void handleTestKO4() throws IOException, InterruptedException {
        HttpRequest richiesta_senzapunto = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/GetUtenti?")).GET().build();
        HttpResponse<String> response = client.send(richiesta_senzapunto, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
    }
    @Test
    public void handleTestOK() throws IOException, InterruptedException {
        Amministratore admin = new Amministratore("Tizio", "pw", "tizio@opinione360.it", "TZ1");
        adminDao.insertAmministratore(admin);
        HttpRequest richiesta_corretta = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/GetUtenti?"+ admin.getId())).GET().build();
        HttpResponse<String> response = client.send(richiesta_corretta, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }
}
package test.it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import it.unipv.ingsfw.opinione360.model.AreaOpzione;
import it.unipv.ingsfw.opinione360.model.IConsultazione;
import it.unipv.ingsfw.opinione360.model.Opzione;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.persistence.SondaggioDAO;
import it.unipv.ingsfw.opinione360.server.ServerSingleton;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetConsultazioneSingolaHandlerTest {
    ServerSingleton server;
    HttpClient client;
    Gson gson;
    SondaggioDAO consDao;
    @Before
    public void init(){
        consDao = new SondaggioDAO();
    	//consDao.reset();
        server = ServerSingleton.getIstance();
        client = HttpClient.newHttpClient();
        gson = new Gson();
    }
    @Test
    public void handleTestKO() throws IOException, InterruptedException {
        server.startServer();
        IConsultazione c = new Sondaggio(UUID.randomUUID());
        HttpRequest richiesta = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Consultazione?"+ c.getId())).GET().build();
        HttpResponse<String> response = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
        assertEquals(404, response.statusCode());
        assertEquals("Consultazione non trovata.", response.body());
    }
    @Test
    public void handleTestKO2() throws IOException, InterruptedException {
        HttpRequest richiestaSenzaConsultazione = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Consultazione")).GET().build();
        HttpResponse<String> response = client.send(richiestaSenzaConsultazione, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
        assertEquals("Manca l'id della consultazione richiesta.", response.body());
    }
    @Test
    public void handleTestKO3() throws IOException, InterruptedException {
        HttpRequest richiestaRandomString = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Consultazione?acaso")).GET().build();
        HttpResponse<String> response = client.send(richiestaRandomString, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
        assertEquals("Manca l'id della consultazione richiesta.", response.body());
    }
    @Test
    public void handleTestKO4() throws IOException, InterruptedException {
        HttpRequest richiestaSenzaPunto = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Consultazione")).GET().build();
        HttpResponse<String> response = client.send(richiestaSenzaPunto, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
        assertEquals("Manca l'id della consultazione richiesta.", response.body());
    }
    @Test
    public void handleTestOK() throws IOException, InterruptedException {
        ArrayList<AreaOpzione> listaOpzioni = new ArrayList<>();
        listaOpzioni.add(new AreaOpzione(new Opzione(3, "Test")));
        Sondaggio c = new Sondaggio(UUID.randomUUID(), "Test?", listaOpzioni, new ArrayList<>(), new GregorianCalendar(), new GregorianCalendar());
        consDao.insertSondaggio(c);
        HttpRequest richiesta_corretta = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Consultazione?"+ c.getId())).GET().build();
        HttpResponse<String> response = client.send(richiesta_corretta, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
    }
}
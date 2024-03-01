package test.it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import it.unipv.ingsfw.opinione360.model.IConsultazione;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.model.Votante;
import it.unipv.ingsfw.opinione360.persistence.SondaggioDAO;
import it.unipv.ingsfw.opinione360.persistence.UtenteDAO;
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

public class VotoHandlerTest {
    ServerSingleton server;
    HttpClient client;
    Gson gson;
    UtenteDAO utenteDao;
    SondaggioDAO sondaggioDao;
    URI uri = URI.create("http://127.0.0.1:60000/Vota");
    @Before
    public void init(){
        utenteDao = new UtenteDAO();
        sondaggioDao = new SondaggioDAO();
    	//utenteDao.reset();
        server = ServerSingleton.getIstance();
        client = HttpClient.newHttpClient();
        gson = new Gson();
    }
    @Test
    public void handleTestKO() throws IOException, InterruptedException {
        server.startServer();
        Utente u = new Utente();
        HttpRequest r = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(u))).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
    }
    @Test
    public void handleTestKO2() throws IOException, InterruptedException {
        HttpRequest r = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString("   ")).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
    }

     @Test
    public void handleTestOK() throws IOException, InterruptedException {
        Votante u = new Votante("Tizio","pw","tizio@opinione360.it", "TC1");
        ArrayList< Votante> votanti = new ArrayList<>();
        votanti.add(u);
        Sondaggio cons = new Sondaggio(UUID.randomUUID(), "Test", new ArrayList<>(), votanti, new GregorianCalendar(), new GregorianCalendar());
        utenteDao.insertUtente(u);
        sondaggioDao.insertSondaggio(cons);
        HttpRequest r = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(u))).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }



}

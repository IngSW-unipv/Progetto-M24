package test.it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import it.unipv.ingsfw.opinione360.model.*;
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

public class CaricaVetrinaHandlerTest {
    ServerSingleton server;
    HttpClient client;
    Gson gson;
    UtenteDAO utenteDao;
    SondaggioDAO sondaggioDAO;
    Sondaggio sondaggio;
    URI uri = URI.create("http://127.0.0.1:60000/Carica_vetrina");
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
        HttpRequest richiestaSoloUtente = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(u))).build();
        HttpResponse<String> response = client.send(richiestaSoloUtente, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
    }
    @Test
    public void handleTestKO2() throws IOException, InterruptedException {
        HttpRequest richiestaSenzaDati = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString("   ")).build();
        HttpResponse<String> response = client.send(richiestaSenzaDati, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
        assertEquals("Dati mancanti", response.body());
    }

    @Test
    public void handleTestKO3() throws IOException, InterruptedException {
        Utente u = new Utente();
        Utente u2 = new Utente();
        ArrayList<AreaOpzione> listaOpzioni = new ArrayList<>();
        listaOpzioni.add(new AreaOpzione(u2, new Opzione(1)));
        listaOpzioni.add(new AreaOpzione(u2, new Opzione(2)));
        ArrayList<Votante> votanti = new ArrayList<>();
        Sondaggio c = new Sondaggio(UUID.randomUUID(), "Domanda", listaOpzioni, votanti, new GregorianCalendar(), new GregorianCalendar());
        IContenuto cont = new ContenutoTesto();

        String body = gson.toJson(u) + "\n" + gson.toJson(c) + "\n" + gson.toJson(cont);
        HttpRequest richiestaUtenteSenzaAccessso = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Carica_vetrina/txt")).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> response = client.send(richiestaUtenteSenzaAccessso, HttpResponse.BodyHandlers.ofString());
        assertEquals(401, response.statusCode());
        assertEquals("L'utente non ha accesso alla consultazione.", response.body());
    }

    @Test
    public void handleTestKO4() throws IOException, InterruptedException {
        Utente u = new Utente("test", "pass");
        Utente u2 = new Utente();
        ArrayList<AreaOpzione> listaOpzioni = new ArrayList<>();
        listaOpzioni.add(new AreaOpzione(u, new Opzione(1)));
        listaOpzioni.add(new AreaOpzione(u2, new Opzione(2)));
        ArrayList<Votante> votanti = new ArrayList<>();
        Sondaggio c = new Sondaggio(UUID.randomUUID(), "Domanda", listaOpzioni, votanti, new GregorianCalendar(), new GregorianCalendar());
        IContenuto cont = new ContenutoTesto();

        String body = gson.toJson(u) + "\n" + gson.toJson(c) + "\n" + gson.toJson(cont);
        HttpRequest richiestaUtenteSenzaAccessso = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/Carica_vetrina/txt")).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> response = client.send(richiestaUtenteSenzaAccessso, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        assertEquals("L'utente non ha accesso alla consultazione.", response.body());
    }

}

package test.it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import it.unipv.ingsfw.opinione360.model.Amministratore;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.IConsultazioneDAO;
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

import static org.junit.Assert.assertEquals;

public class CreaConsultazioneHandlerTest {
    ServerSingleton server;
    HttpClient client;
    Gson gson;
    IConsultazioneDAO consultazioneDao;
    URI uri = URI.create("http://127.0.0.1:60000//Crea_consultazione/Sondaggio");
    Sondaggio sondaggio;
    @Before
    public void init(){
        //consultazioneDao = new SondaggioDAO();
    	//consultazioneDao.reset();
        server = ServerSingleton.getIstance();
        client = HttpClient.newHttpClient();
        gson = new Gson();
        Utente u1 = new Utente();
        Utente u2 = new Utente();
        ArrayList<Utente> votanti = new ArrayList<>();
        votanti.add(u1);
        votanti.add(u2);
        ArrayList<Utente> candidati = new ArrayList<>();
        votanti.add(u1);
        sondaggio = new Sondaggio("Test", votanti, candidati,new String[]{"1", "2"}, new GregorianCalendar());

    }
    @Test
    public void handleTestKO() throws IOException, InterruptedException {
        server.startServer();
        Utente u = new Utente();
        HttpRequest r = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(u))).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(404, response.statusCode());
    }
    @Test
    public void handleTestKO2() throws IOException, InterruptedException {
        Amministratore u = new Amministratore("Test","pass","adm@o.it", "1");
        HttpRequest r = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(u) + "\n"+ "   ")).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
    }

     @Test
    public void handleTestOK() throws IOException, InterruptedException {
        Utente u = new Utente("TestAdmin","pass","email@opinione360.it", "1");
        String body = gson.toJson(u) + "\n" + gson.toJson(sondaggio);
        HttpRequest r = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }



}

package test.it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;

import it.unipv.ingsfw.opinione360.model.Amministratore;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.AmministratoreDAO;
import it.unipv.ingsfw.opinione360.persistence.UtenteDAO;
import it.unipv.ingsfw.opinione360.server.ServerSingleton;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetUtentiHandlerTest {
    ServerSingleton server;
    HttpClient client;
    Gson gson;
    AmministratoreDAO adminDao;
    Amministratore admin;
    @Before
    public void init(){
        adminDao = new AmministratoreDAO();
        adminDao.reset();
        server = ServerSingleton.getIstance();
        client = HttpClient.newHttpClient();
        gson = new Gson();
        admin = new Amministratore("AdminTest", "pass");
        adminDao.insertAmministratore(admin);
    }
    @Test
    public void handleTestOK() throws IOException, InterruptedException, SQLException {
        server.startServer();
        Amministratore adminIn = adminDao.selectById(admin);
        HttpRequest richiestaAdmininEsistente = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/GetUtenti?"+ adminIn.getId())).GET().build();
        HttpResponse<String> response = client.send(richiestaAdmininEsistente, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
    }
    @Test
    public void handleTestKO2() throws IOException, InterruptedException {
        HttpRequest richiestaSenzaAdmin = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/GetUtenti")).GET().build();
        HttpResponse<String> response = client.send(richiestaSenzaAdmin, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
        assertEquals("Manca l'amministratore che richiede", response.body());
    }
    @Test
    public void handleTestKO3() throws IOException, InterruptedException {
        HttpRequest richiestaRandomString = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/GetUtenti?acaso")).GET().build();
        HttpResponse<String> response = client.send(richiestaRandomString, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
        assertEquals("Manca l'amministratore che richiede", response.body());
    }
    @Test
    public void handleTestKO4() throws IOException, InterruptedException {
        HttpRequest richiestaSenzaPunto = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/GetUtenti")).GET().build();
        HttpResponse<String> response = client.send(richiestaSenzaPunto, HttpResponse.BodyHandlers.ofString());
        assertEquals(400, response.statusCode());
        assertEquals("Manca l'amministratore che richiede", response.body());
    }
    @Test
    public void handleTestKO5() throws IOException, InterruptedException {
        Utente utente = new Utente("NormalUser", "pw");
        UtenteDAO ud = new UtenteDAO();
        ud.insertUtente(utente);
        HttpRequest richiestaUtenteNormale = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/GetUtenti?"+ utente.getId())).GET().build();
        HttpResponse<String> response = client.send(richiestaUtenteNormale, HttpResponse.BodyHandlers.ofString());
        assertEquals(404, response.statusCode());
        assertEquals("Impossibile trovare l'utente specificato.", response.body());
    }

    @Test
    public void handleTestKO6() throws IOException, InterruptedException {
        Amministratore fakeAdmin = new Amministratore("Admin", "pw");
        HttpRequest richiestaAdminInesistente = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:60000/GetUtenti?"+ fakeAdmin.getId())).GET().build();
        HttpResponse<String> response = client.send(richiestaAdminInesistente, HttpResponse.BodyHandlers.ofString());
        assertEquals(404, response.statusCode());
        assertEquals("Impossibile trovare l'utente specificato.", response.body());
    }
}
package it.unipv.ingsfw.opinione360;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import it.unipv.ingsfw.opinione360.exception.ServerException;
import it.unipv.ingsfw.opinione360.exception.UserMissingAccessException;
import it.unipv.ingsfw.opinione360.model.IConsultazioneC;
import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.model.UtenteC;
import it.unipv.ingsfw.opinione360.exception.ConsultationExpiredException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Calendar;

public class ServerConnection {
    private String conn;
    private HttpClient client;
    private int statusCode;
    private Gson gson;

    /**
     * Costruttore
     */
    public ServerConnection(){
        conn = "http://127.0.0.1:60000";
        client = HttpClient.newHttpClient();
        gson = new Gson();
    }

    /**
     * Metodo che permette di inviare al server una richiesta di signup
     * @param utenteIn l'utente che vuole registrarsi
     * @throws ServerException
     * @return la risposta del server
     */
    public String signup(UtenteC utenteIn) throws ServerException {
        String responseBody;
        URI uri = init("/Signup");
        String body = gson.toJson(utenteIn);
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        try {
            HttpResponse<String> risposta = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
            statusCode = risposta.statusCode();
            responseBody = risposta.body();

            if(statusCode == 200){
                System.out.println(responseBody);
                return responseBody;
            }
            if(statusCode == 500)
                throw new ServerException();
            if(statusCode == 400)
                System.out.println(responseBody);

        } catch (IOException e) {
            System.out.println("Errore nell'IO");
            System.out.println(e.getMessage());
        } catch(InterruptedException exc){
          exc.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo che permette di inviare al server una richiesta di login
     * @param utenteIn l'utente che vuole fare il login
     * @throws ServerException
     * @throws IOException
     * @return l'utente che ha fatto il login
     */
    public UtenteC login(UtenteC utenteIn) throws ServerException, IOException {
        String responseBody;
        URI uri = init("/Login");
        String body = gson.toJson(utenteIn);
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        try {
            HttpResponse<String> risposta = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
            statusCode = risposta.statusCode();
            responseBody = risposta.body();
            System.out.println(risposta.statusCode());

            if(statusCode == 200){
                System.out.println(responseBody);
                return gson.fromJson(risposta.body(), UtenteC.class);
            }
            if(statusCode == 500)
                throw new ServerException();
            if(statusCode == 400)
                throw new IOException(responseBody);
            if(statusCode == 404)
                System.out.println(responseBody);
        } catch(InterruptedException exc){
          exc.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo che permette di inviare al server una richiesta di votazione
     * @param consultazione la consultazione in cui si vuole votare
     * @param id_opzioni le scelte
     * @throws UserMissingAccessException
     * @throws ConsultationExpiredException
     * @return la risposta del server
     */
    public String vota(UtenteC utente, ArrayList<Integer> id_opzioni, IConsultazioneC consultazione) throws Exception {
        URI uri = init("/Vota");
        String body = gson.toJson(utente)+ "\n" + gson.toJson(id_opzioni.get(0)) + "\n" + gson.toJson(consultazione) ;
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).POST( HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> risposta = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
        statusCode = risposta.statusCode();

        if(statusCode == 200)
            return risposta.body();
        if(statusCode == 401)
            throw new UserMissingAccessException();
        if(statusCode == 418){
            System.out.println("I'm a teapot");
            throw new ConsultationExpiredException();
        }
        if(statusCode == 500)
            throw new Exception("Errore del server.");
        else return null;
    }

    /**
     * Metodo che richiede al server la lista delle consultazioni attive
     * @return un'ArrayList di SondaggioC
     */
    public ArrayList <SondaggioC> getConsultazioni() {
        URI uri = init ("/Consultazioni_lista");
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).GET().build();
        try {
            HttpResponse<String> response = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
            statusCode = response.statusCode();

            if(statusCode == 200){
                TypeToken<ArrayList<SondaggioC>> collectionType = new TypeToken<>(){};
                return gson.fromJson(response.body(), collectionType);
            }
            else
                System.out.println("errore");

        } catch (IOException e) {
            System.out.println("Errore nell'IO");
            System.out.println(e.getMessage());
        } catch( InterruptedException exc){
          exc.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo che richiede al server un Sondaggio
     * @return il Sondaggio richiesto
     */
     public SondaggioC getSondaggio() {
        URI uri = init("/Consultazioni_lista");
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).GET().build();
        try {
            HttpResponse<String> response = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
            statusCode = response.statusCode();

            if(statusCode == 200)
                return gson.fromJson(response.body(), SondaggioC.class);
            else
                System.out.println("errore");
        } catch (IOException e) {
            System.out.println("Errore nell'IO");
            System.out.println(e.getMessage());
        } catch( InterruptedException exc){
          exc.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo che permette di inviare al server una richiesta della lista di utenti
     * @param utenteIn l'amministratore che vuole fare la richiesta
     * @throws ServerException
     * @throws IOException
     */
    public ArrayList<UtenteC> getUtenti(UtenteC utenteIn) throws ServerException, IOException {
        String responseBody;
        String path = "/GetUtenti?" + utenteIn.getId();
        URI uri = init(path);
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).GET().build();
        try {
            HttpResponse<String> risposta = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
            statusCode = risposta.statusCode();
            responseBody = risposta.body();

            if(statusCode == 200){
                TypeToken<ArrayList<UtenteC>> collectionType = new TypeToken<>(){};
                return gson.fromJson(responseBody, collectionType);
            }
            if(statusCode == 500)
                throw new ServerException();
            if(statusCode == 400)
                throw new IOException(responseBody);
            if(statusCode == 404)
                System.out.println(responseBody);
        } catch(InterruptedException exc){
          exc.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo che permette di inviare al server una richiesta di votazione
     * @param admin
     * @param votanti
     * @param candidati
     * @param opzioni
     * @param data_apertura
     * @param data_chiusura
     * @return
     * @throws Exception
     */
    public String creaConsultazione(UtenteC admin, ArrayList<Integer> votanti, ArrayList<Integer> candidati, int [] opzioni, Calendar data_apertura, Calendar data_chiusura) throws Exception {
        URI uri = init("/CreaConsultazione");
        String body = gson.toJson(admin)+ "\n" + gson.toJson(votanti) + "\n" + gson.toJson(candidati) + "\n" + gson.toJson(opzioni) + "\n" + gson.toJson(candidati) ;
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).POST( HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> risposta = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
        statusCode = risposta.statusCode();

        if(statusCode == 200)
            return risposta.body();
        if(statusCode == 404)
            throw new UserMissingAccessException();
        if(statusCode == 500)
            throw new Exception("Errore del server.");
        else return null;
    }

    private URI init(String path){
         String connection = conn + path;
         return URI.create(connection);
    }
}
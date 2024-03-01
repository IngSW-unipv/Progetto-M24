package it.unipv.ingsfw.opinione360;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import it.unipv.ingsfw.opinione360.exception.*;
import it.unipv.ingsfw.opinione360.model.*;
import it.unipv.ingsfw.opinione360.util.CustomDeserializer;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Classe che consente di gestire gli scambi di informazioni con il server HTTP
 */
public class ServerConnection {
    private String conn;
    private HttpClient client;
    private int statusCode;
    private String responseBody;
    private Gson gson;

    public ServerConnection(){
        client = HttpClient.newHttpClient();
        gson = new Gson();
        init();
    }

    /**
     * Metodo che permette di inviare al server una richiesta di signup
     * @param utenteIn l'utente che vuole registrarsi
     * @return un oggetto di tipo {@link it.unipv.ingsfw.opinione360.model.UtenteC}
     * @throws ServerException
     * @throws IOException
     */
    public UtenteC signup (IUtenteC utenteIn) throws ServerException, IOException, AlreadyRegisteredUserException {
        URI uri = configPath("/Signup");
        String body = gson.toJson(utenteIn);
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        try {
            HttpResponse<String> risposta = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
            statusCode = risposta.statusCode();
            responseBody = risposta.body();
            if(statusCode == 200)
                return gson.fromJson(responseBody, UtenteC.class);
            if(statusCode == 406)
                throw new AlreadyRegisteredUserException();
            if(statusCode == 400)
                throw new IOException(responseBody);
            if(statusCode == 500)
                throw new ServerException();
        } catch(InterruptedException exc){
          exc.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo che permette di inviare al server una richiesta di login
     * @param utenteIn l'utente che vuole fare il login
     * @return un oggetto di tipo {@link it.unipv.ingsfw.opinione360.model.UtenteC}
     * @throws ServerException
     * @throws UserNotFoundException
     * @throws IOException
     */
    public IUtenteC login (IUtenteC utenteIn) throws ServerException, UserNotFoundException, IOException {
        URI uri = configPath("/Login");
        String body = gson.toJson(utenteIn);
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        try {
            HttpResponse<String> risposta = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
            statusCode = risposta.statusCode();
            responseBody = risposta.body();

            if(statusCode == 200) {
                Gson gsonOut = new GsonBuilder().registerTypeAdapter(IUtenteC.class, new CustomDeserializer()).create();
                return gsonOut.fromJson(responseBody, IUtenteC.class);
            }
            if(statusCode == 500)
                throw new ServerException();
            if(statusCode == 400)
                throw new IOException(responseBody);
            if(statusCode == 404)
                throw new UserNotFoundException();
        } catch(InterruptedException exc){
          exc.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo che permette di inviare al server una richiesta di votazione
     * @param utente l'utente che vuole votare
     * @param id_opzioni
     * @param consultazione la consultazione su cui vuole votare
     * @return la risposta del server
     * @return
     * @throws Exception
     */
    public String vota (IUtenteC utente, OpzioneC[] id_opzioni, IConsultazioneC consultazione) throws UserMissingAccessException,ConsultationExpiredException, ServerException, IOException {
        URI uri = configPath("/Vota");
        String body = gson.toJson(utente)+ "\n" + gson.toJson(id_opzioni) + "\n" + gson.toJson(consultazione) ;
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).POST( HttpRequest.BodyPublishers.ofString(body)).build();
        try {
            HttpResponse<String> risposta = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
            statusCode = risposta.statusCode();
            responseBody = risposta.body();

            if (statusCode == 200)
                return responseBody;
            if (statusCode == 401)
                throw new UserMissingAccessException();
            if (statusCode == 418)
                throw new ConsultationExpiredException();
            if (statusCode == 500)
                throw new ServerException();

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo che richiede al server la lista delle consultazioni attive
     * @return un'ArrayList di SondaggioC
     */
    public ArrayList <SondaggioC> getConsultazioni(IUtenteC userIn) throws IOException{
        URI uri = configPath("/Consultazioni_lista"+ userIn.getId());
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).GET().build();
        try {
            HttpResponse<String> risposta = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
            statusCode = risposta.statusCode();
            responseBody = risposta.body();

            if(statusCode == 200){
                TypeToken<ArrayList<SondaggioC>> collectionType = new TypeToken<>(){};
                return gson.fromJson(responseBody, collectionType);
            }
           if(statusCode == 400)
               throw new IOException();

        } catch( InterruptedException exc){
          exc.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo che permette di ottenere una specifica consultazione in base all'ID
     * @param consIn
     * @return un oggetto di tipo {@link it.unipv.ingsfw.opinione360.model.IConsultazioneC}
     * @throws ServerException
     * @throws IOException
     */
     public IConsultazioneC getConsultazione (IConsultazioneC consIn) throws ServerException, IOException, RuntimeException {
         String path = "/Consultazione?" + consIn.getId();
         URI uri = configPath(path);
         HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).GET().build();
        try {
            HttpResponse<String> risposta = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
            statusCode = risposta.statusCode();
            responseBody = risposta.body();
            if(statusCode == 200) {
                Gson gsonOut = new GsonBuilder().registerTypeAdapter(IConsultazioneC.class, new CustomDeserializer()).create();
                return gsonOut.fromJson(responseBody, IConsultazioneC.class);
            }
            if(statusCode == 404)
                throw new RuntimeException(responseBody);
            if(statusCode == 400)
                throw new IOException(responseBody);
            if(statusCode == 500)
                throw new ServerException();

        } catch( InterruptedException exc){
          exc.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo che permette di inviare al server una richiesta della lista di utenti
     * @param utenteIn l'amministratore che vuole fare la richiesta
     * @return un oggetto di tipo ArrayList<UtenteC>
     * @throws ServerException
     * @throws IOException
     * @throws UserNotFoundException
     */
    public ArrayList<UtenteC> getUtenti (IUtenteC utenteIn) throws ServerException, IOException, UserNotFoundException {
        String path = "/GetUtenti?" + utenteIn.getId();
        URI uri = configPath(path);
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
                throw new UserNotFoundException();
        } catch(InterruptedException exc){
          exc.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo che consente di inviare al server una richiesta di creazione di una consultazione
     * @param admin l'amministratore che vuole creare la consultazione
     * @return la risposta del server
     * @throws UserMissingAccessException
     * @throws ServerException
     * @throws IOException
     */
    public String creaConsultazione (IUtenteC admin, IConsultazioneC cons) throws UserMissingAccessException, ServerException, IOException, IllegalArgumentException {
        URI uri = configPath("/Crea_consultazione/Sondaggio");
        String body = gson.toJson(admin) + "\n" + gson.toJson(cons);
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).POST( HttpRequest.BodyPublishers.ofString(body)).build();
        try {
            HttpResponse<String> risposta = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
            statusCode = risposta.statusCode();
            responseBody = risposta.body();
            if (statusCode == 200)
                return responseBody;
            if(statusCode == 406)
                throw new IllegalArgumentException(responseBody);
            if (statusCode == 404)
                throw new UserMissingAccessException();
            if(statusCode == 400)
                throw new IOException();
            if (statusCode == 500)
                throw new ServerException();
        }catch (InterruptedException exc){
            exc.printStackTrace();
        }
        return null;
    }

    //TODO da finire
    /**
     * Metodo che permette il caricamento di un contenuto in una vetrina
     * @param utente l'utente che vuole caricare i contenuti
     * @param cons la consultazione in cui vole caricare i contenuti
     * @return la risposta del server
     * @throws UserMissingAccessException
     * @throws ServerException
     * @throws IOException
     */
    public String caricaVetrina (IUtenteC utente, IConsultazioneC cons, IContenutoC contenuto,String tipoContenuto) throws UserMissingAccessException, ServerException, IOException {
        URI uri = configPath("/Carica_vetrina/" + tipoContenuto);
        String body = gson.toJson(utente) + "\n" + gson.toJson(cons) + "\n" + gson.toJson(contenuto);
        HttpRequest richiesta = HttpRequest.newBuilder().uri(uri).POST( HttpRequest.BodyPublishers.ofString(body)).build();
        try {
            HttpResponse<String> risposta = client.send(richiesta, HttpResponse.BodyHandlers.ofString());
            statusCode = risposta.statusCode();
            responseBody = risposta.body();
            if (statusCode == 200)
                return responseBody;
            if (statusCode == 401)
                throw new UserMissingAccessException();
            if (statusCode == 500)
                throw new ServerException();
        }catch (InterruptedException exc){
            exc.printStackTrace();
        }
        return null;
    }

    private URI configPath(String path){
         String connection = conn + path;
         return URI.create(connection);
    }

    private void init(){
        final String URI = "uri";
        try {
            Properties p = new Properties(System.getProperties());
            p.load(new FileInputStream("Client/properties/properties.properties"));
            conn = p.getProperty(URI);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
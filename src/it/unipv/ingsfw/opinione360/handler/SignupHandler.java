package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.IUtenteDAO;
import it.unipv.ingsfw.opinione360.persistence.UtenteDAO;

import java.io.*;
import java.sql.SQLException;

/**
 * Questa classe permette la gestione di un HttpExchange di sign up<br>
 * Accetta un oggetto {@link it.unipv.ingsfw.opinione360.model.Utente} rappresentato tramite Json.
 * @see HttpHandler
 */
public class SignupHandler implements HttpHandler {

    private String messaggio;
    private String risposta;

    /**
     * Costruttore
     */
    public SignupHandler(){
        messaggio = null;
        risposta = null;
    }

    /**
     * Override del metodo dell'interfaccia HttpHandler che gestisce un sign up
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        messaggio = null;
        try {
            InputStream in = exchange.getRequestBody();
            BufferedReader buffread = new BufferedReader(new InputStreamReader(in));
            messaggio = buffread.readLine();
            Utente utente = gson.fromJson(messaggio, Utente.class);
            if(utente!= null){
                System.out.println(utente.getId());
                IUtenteDAO ud = new UtenteDAO();
                ud.insertUtente(utente);
                risposta = "Registrazione avvenuta con successo.";
                exchange.sendResponseHeaders(200, risposta.length());
            }
            else{
                risposta = "Dati mancanti.";
                exchange.sendResponseHeaders(400, risposta.length());
            }
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        } catch (IOException exc) {//(AlreadyRegisteredUserException exc){
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(401, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        } catch (JsonSyntaxException exc){
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(401, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }catch(SQLException exc){
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(500, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }

    }
}
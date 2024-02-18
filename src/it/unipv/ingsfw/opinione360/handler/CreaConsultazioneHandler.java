package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.AmministratoreDAO;
import it.unipv.ingsfw.opinione360.persistence.IAmministratoreDAO;
import it.unipv.ingsfw.opinione360.persistence.ISondaggioDAO;
import it.unipv.ingsfw.opinione360.persistence.SondaggioDAO;
import it.unipv.ingsfw.opinione360.persistence.exception.UserNotFoundException;

import java.io.*;
import java.sql.SQLException;

/**
 * Questa classe permette la gestione di un HttpExchange di creazione di una consultazione
 * @see HttpHandler
 */
public class CreaConsultazioneHandler implements HttpHandler {

    private String messaggio;
    private String risposta;

    /**
     * Costruttore
     */
    public CreaConsultazioneHandler(){
        messaggio = null;
        risposta = null;
    }

    /**
     * Implementazione del metodo dell'interfaccia HttpHandler che gestisce la creazione di una consultazione
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        IAmministratoreDAO ad = new AmministratoreDAO();
        ISondaggioDAO cd = new SondaggioDAO();
        messaggio = null;

        try {
            InputStream in = exchange.getRequestBody();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(in));
            messaggio = in1.readLine();
            ad.selectById(gson.fromJson (messaggio, Utente.class));
            messaggio = in1.readLine();
            cd.insertSondaggio(gson.fromJson(messaggio, Sondaggio.class));

            risposta = "Consultazione creata con successo";
            exchange.sendResponseHeaders(200, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();

        } catch (UserNotFoundException exc) {
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(404, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        } catch(SQLException |IOException exc){
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(500, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }
    }
}
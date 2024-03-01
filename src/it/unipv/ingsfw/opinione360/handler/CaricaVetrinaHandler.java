package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import it.unipv.ingsfw.opinione360.model.*;
import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;
import it.unipv.ingsfw.opinione360.persistence.*;

import java.io.*;
import java.sql.SQLException;

/**
 * Questa classe permette la gestione di un HttpExchange di caricamento di contenuti nella vetrina<br>
 * Accetta un HttpExcange che nel body abbia i seguenti oggetti:<br>
 * Utente/nIConsultazione/ncontenuto<br>
 * in cui Utente, consultazione e contenuto sono serializzati in Json.
 * @see HttpHandler
 */
public class CaricaVetrinaHandler implements HttpHandler {

    private String messaggio;
    private String risposta;

    /**
     * Costruttore
     */
    public CaricaVetrinaHandler(){
        messaggio = null;
        risposta = null;
    }

    /**
     * Implementazione del metodo dell'interfaccia HttpHandler che gestisce il caricamento di contenuti nella vetrina
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        PersistenceFacade pf = PersistenceFacade.getIstance();
        IContenuto contenuto = null;
        messaggio = null;
        String uri = exchange.getRequestURI().toString();

        try {
            InputStream in = exchange.getRequestBody();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(in));
            messaggio = in1.readLine();
            Utente utenteIn = gson.fromJson(messaggio, Utente.class);
            messaggio = in1.readLine();
            IConsultazione consultazione = gson.fromJson(messaggio, Sondaggio.class);
            messaggio = in1.readLine();
            if(uri.equals("/Carica_vetrina/txt"))
                contenuto = gson.fromJson(messaggio, ContenutoTesto.class);
            else if (uri.equals("/Carica_vetrina/img"))
                contenuto = gson.fromJson(messaggio, ContenutoImmagine.class);

            if(utenteIn != null && consultazione != null && contenuto != null){
                Utente utente = pf.selectById(utenteIn);
                IConsultazione cons = pf.selectById(consultazione);
                cons.caricaContenuto(utente, contenuto);
                if(pf.insertContenuti(cons)) {
                    risposta = "Caricamento avvenuto con successo.";
                    exchange.sendResponseHeaders(200, risposta.length());
                }
                else{
                    risposta = "Caricamento non riuscito.";
                    exchange.sendResponseHeaders(500, risposta.length());
                }
            }
            else{
                risposta = "Dati mancanti";
                exchange.sendResponseHeaders(400, risposta.length());
            }
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();

        } catch (UserMissingAccessException exc) {
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(401, 0);
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        } catch (Exception exc) {
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(500, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }
    }
}
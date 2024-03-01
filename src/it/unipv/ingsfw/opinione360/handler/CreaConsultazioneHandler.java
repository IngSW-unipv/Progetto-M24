package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import it.unipv.ingsfw.opinione360.model.IConsultazione;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.*;
import it.unipv.ingsfw.opinione360.persistence.exception.UserNotFoundException;

import java.io.*;

/**
 * Questa classe permette la gestione di un HttpExchange di creazione di una consultazione<br>
 * Accetta un HttpExcange che nel body abbia i seguenti oggetti:<br>
 * Ammministratore/nIConsultazione<br>
 * in cui Amministratore e consultazione sono serializzati in Json.
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
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        PersistenceFacade pf = PersistenceFacade.getIstance();
        IConsultazione consIn = null;
        messaggio = null;
        String uri = exchange.getRequestURI().toString();

        try {
            InputStream in = exchange.getRequestBody();
            BufferedReader buffin = new BufferedReader(new InputStreamReader(in));
            messaggio = buffin.readLine();
            pf.selectById(gson.fromJson (messaggio, Utente.class));
            messaggio = buffin.readLine();
            if(uri.equals("/Crea_consultazione/Sondaggio"))
                consIn = gson.fromJson(messaggio, Sondaggio.class);
            /*else if (uri.equals("/Crea_consultazione/Votazione"))
                consIn = gson.fromJson(messaggio, Sondaggio.class); //Votazione.class*/

            if(consIn != null) {
                if (pf.insertConsultazione(consIn)) {
                    risposta = "Consultazione creata con successo";
                    exchange.sendResponseHeaders(200, risposta.length());
                } else {
                    risposta = "Impossibile creare la consultazione.";
                    exchange.sendResponseHeaders(406, risposta.length());
                }
            }
            else{
                risposta = "Dati mancanti.";
                exchange.sendResponseHeaders(400, risposta.length());
            }
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();

        } catch (UserNotFoundException exc) {
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(404, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        } catch(Exception exc){
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(500, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }
    }
}
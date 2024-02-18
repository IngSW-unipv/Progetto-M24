package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;
import it.unipv.ingsfw.opinione360.persistence.IUtenteDAO;

import java.io.*;

/**
 * Questa classe permette la gestione di un HttpExchange di caricamento di contenuti nella vetrina
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
        messaggio = null;
        try {
            InputStream in = exchange.getRequestBody();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(in));
            messaggio = in1.readLine();
            Utente utente = gson.fromJson(messaggio, Utente.class);
            messaggio = in1.readLine();
            Sondaggio consultazione = gson.fromJson(messaggio, Sondaggio.class);
            messaggio = in1.readLine();
            String contenuti =  gson.fromJson(messaggio, String.class);
            if(utente!= null && consultazione !=null && contenuti !=null){
                //utente = IUtenteDAO.selectUtente(utente);
                consultazione.caricaContenuto(utente, contenuti);
                    //IConsultazioneDAO.insertContenuti(consultazione);
                    risposta = "Registrazione avvenuta con successo";
                    exchange.sendResponseHeaders(200, risposta.length());
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
            exchange.sendResponseHeaders(401, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }
    }
}
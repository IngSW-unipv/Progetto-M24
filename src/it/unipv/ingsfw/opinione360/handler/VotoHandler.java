package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.model.exception.ConsultationExpiredException;
import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;
import it.unipv.ingsfw.opinione360.persistence.ISondaggioDAO;
import it.unipv.ingsfw.opinione360.persistence.IUtenteDAO;
import it.unipv.ingsfw.opinione360.persistence.SondaggioDAO;
import it.unipv.ingsfw.opinione360.persistence.UtenteDAO;

import java.io.*;
import java.sql.SQLException;

/**
 * Questa classe permette la gestione di un HttpExchange di votazione
 * Accetta un HTTPExchange che abbia abbia nel body i seguenti oggetti:<br>
 * Utente/nConsultazione/nscelta<br>
 * in cui Utente, Consultazione e sceleta sono rappresentati in formato Json.
 * @see com.sun.net.httpserver.HttpHandler
 */
public class VotoHandler implements HttpHandler {

    private String messaggio;
    private String risposta;

    /**
     * Costruttore
     */
    public VotoHandler(){
        messaggio = null;
        risposta = null;
    }

    /**
     * Implementazione del metodo dell'interfaccia HttpHandler che gestisce una votazione
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        messaggio = null;
        IUtenteDAO ud = new UtenteDAO();
        ISondaggioDAO sd = new SondaggioDAO();
        Gson gson = new Gson();

        try{
            InputStream in = exchange.getRequestBody();
            BufferedReader buffread = new BufferedReader(new InputStreamReader(in));
            messaggio = buffread.readLine();
            Utente utente = gson.fromJson(messaggio, Utente.class);
            messaggio = buffread.readLine();
            int scelta = gson.fromJson(messaggio, int.class);
            messaggio = buffread.readLine();
            Sondaggio consultazione = gson.fromJson(messaggio, Sondaggio.class);

            if(utente!=null && consultazione != null){
                utente = ud.selectById(utente);
                consultazione = sd.selectById(consultazione);
                consultazione.vota(scelta, utente);
                sd.updateSondaggio(consultazione);
                risposta = "Voto acquisito correttamente";
                exchange.sendResponseHeaders(200, risposta.length());
            }
            else{
                risposta = "Dati mancanti.";
                exchange.sendResponseHeaders(400, risposta.length());
            }
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();

        } catch (UserMissingAccessException exc){
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(401, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        } catch (ConsultationExpiredException exc){
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(418, 0); //I'm a trapot
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        } catch (SQLException | IOException exc) {
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(500, 0);
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }
    }
}
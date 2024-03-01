package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.unipv.ingsfw.opinione360.model.IConsultazione;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.*;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Questa classe permette la gestione di un HttpExchange di richiesta della lista di utenti da parte di un amministartore<br>
 * Accetta un HttpExchange che abbia nel path l'id (che deve essere di tipo {@link UUID}) dell'admin.
 * @see HttpHandler
 */
public class GetConsultazioniHandler implements HttpHandler {

    private String risposta;

    /**
     * Costruttore
     */
    public GetConsultazioniHandler(){
        risposta = null;
    }

    /**
     * Implementazione del metodo dell'interfaccia HttpHandler che gestisce una richiesta di utenti
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        Utente userIn;
        PersistenceFacade pf = PersistenceFacade.getIstance();
        ArrayList<IConsultazione> listaConsultazioni;
       String regEx = "/Consultazioni_lista\\?[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}$";
        try {
            //String uri = exchange.getRequestURI().toString();
           // Matcher m = Pattern.compile(regEx).matcher(uri);
            //if(m.matches()){
               // String[] id = uri.split("\\?");
                //userIn = pf.selectById(new Utente(UUID.fromString(id[1])));
                //listaConsultazioni = pf.selectListaConsultazioni(userIn);

                listaConsultazioni = pf.selectListaConsultazioni();
                risposta = gson.toJson(listaConsultazioni);
                exchange.sendResponseHeaders(200, 0);
            //}
            //else{
              //  risposta = "Manca l'id dell'utente.";
               // exchange.sendResponseHeaders(400, risposta.length());
            //}
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();

        }catch (Exception exc){
            risposta =exc.getMessage();
            exchange.sendResponseHeaders(500, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }
    }
}
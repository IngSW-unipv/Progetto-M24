package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import it.unipv.ingsfw.opinione360.dto.UtenteDTO;
import it.unipv.ingsfw.opinione360.dto.UtenteMapper;
import it.unipv.ingsfw.opinione360.model.Amministratore;
import it.unipv.ingsfw.opinione360.persistence.*;
import it.unipv.ingsfw.opinione360.persistence.exception.UserNotFoundException;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Questa classe permette la gestione di un HttpExchange di richiesta dellea lista di utenti da parte di un amministartore
 * @see HttpHandler
 */
public class GetUtentiHandler implements HttpHandler {

    private String risposta;

    /**
     * Costruttore
     */
    public GetUtentiHandler(){
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
        IUtenteDAO ud = new UtenteDAO();
        ArrayList<UtenteDTO> listaUtenti;
        String regEx = "/GetUtenti\\?[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}$";

        try {
            String uri = exchange.getRequestURI().toString();
            Matcher m = Pattern.compile(regEx).matcher(uri);
            if(m.matches()){
                String[] id = uri.split("\\?");
                Amministratore admin = new Amministratore(UUID.fromString(id[1]));
                IAmministratoreDAO ad = new AmministratoreDAO();
                ad.selectById(admin);

                listaUtenti = UtenteMapper.entityCollectionToDto(ud.selectAll());
                risposta = gson.toJson(listaUtenti);
                exchange.sendResponseHeaders(200, risposta.length());
            } else{
                risposta = "Manca l'amministratore che richiede";
                exchange.sendResponseHeaders(400, risposta.length());
            }
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();

        } catch(UserNotFoundException exc) {
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(404, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        } catch (SQLException | IOException exc) {
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(500, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }
    }
}
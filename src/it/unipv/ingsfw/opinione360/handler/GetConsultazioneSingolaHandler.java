package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import it.unipv.ingsfw.opinione360.dto.SondaggioDTO;
import it.unipv.ingsfw.opinione360.dto.SondaggioMapper;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.persistence.ISondaggioDAO;
import it.unipv.ingsfw.opinione360.persistence.SondaggioDAO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Questa classe permette la gestione di un HttpExchange di richiesta di una consultazione
 * @see HttpHandler
 */
public class GetConsultazioneSingolaHandler implements HttpHandler {

    private String risposta;

    /**
     * Costruttore
     */
    public GetConsultazioneSingolaHandler(){
        risposta = null;
    }

    /**
     * Implementazione del metodo dell'interfaccia HttpHandler che gestisce una richiesta di una specifica consultazione
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        ISondaggioDAO sd = new SondaggioDAO();
        String regEx = "/GetUtenti\\?[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}$";

        try {
            String uri = exchange.getRequestURI().toString();
            Matcher m = Pattern.compile(regEx).matcher(uri);
            if(m.matches()){
                String[] id = uri.split("\\?");
                Sondaggio sondaggio = new Sondaggio(UUID.fromString(id[1]));

                SondaggioDTO u = SondaggioMapper.entityToDto(sd.selectById(sondaggio));
                risposta = gson.toJson(u);
                exchange.sendResponseHeaders(200, risposta.length());
            } else{
                risposta = "Manca l'id della consultazione richiesta.";
                exchange.sendResponseHeaders(400, risposta.length());
            }
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();

        } catch (IOException exc) {
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(400, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }
    }
}
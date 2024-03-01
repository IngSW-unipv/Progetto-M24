package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import it.unipv.ingsfw.opinione360.dto.ConsultazioneMapper;
import it.unipv.ingsfw.opinione360.model.IConsultazione;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.persistence.PersistenceFacade;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Questa classe permette la gestione di un HttpExchange di richiesta di una consultazione<br>
 * Accetta un HttpExchange che abbia nel path l'id (che deve essere di tipo {@link java.util.UUID}) della consultazione desiderata.
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
        PersistenceFacade pf = PersistenceFacade.getIstance();
        String regEx = "/Consultazione\\?[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}$";
        IConsultazione consultazione;
        try {
            String uri = exchange.getRequestURI().toString();
            Matcher m = Pattern.compile(regEx).matcher(uri);
            if(m.matches()){
                String[] id = uri.split("\\?");
                consultazione = pf.selectById(new Sondaggio(UUID.fromString(id[1])));
                risposta = gson.toJson(ConsultazioneMapper.entityToDto(consultazione));
                exchange.sendResponseHeaders(200, risposta.length());
            }
            else{
                risposta = "Manca l'id della consultazione richiesta.";
                exchange.sendResponseHeaders(400, risposta.length());
            }
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();

        } catch (IllegalArgumentException exc){
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(404, risposta.length());
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
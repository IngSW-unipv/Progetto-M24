package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.unipv.ingsfw.opinione360.dto.SondaggioDTO;
import it.unipv.ingsfw.opinione360.dto.SondaggioMapper;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.ISondaggioDAO;
import it.unipv.ingsfw.opinione360.persistence.SondaggioDAO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Questa classe permette la gestione di un HttpExchange di richiesta di consultazioni
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
     * Implementazione del metodo dell'interfaccia HttpHandler che gestisce una richiesta delle consultazioni
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        ISondaggioDAO sd = new SondaggioDAO();

        try {
            System.out.println(exchange.getRequestURI());

            Utente u3 = new Utente("Tizio Caio", "password", "tiziocaio@nonso.bho", "TC1200802");
            ArrayList<Utente> v = new ArrayList<>();
            v.add(u3);
            String[] opzioni = {"Michael Schumacher", "Lewis Hamilton", "Ayrton Senna", "Sebastian Vettel", "Alain Prost"};
            Sondaggio sondaggio1 = new Sondaggio("Miglior pilota di Formula 1?",v,v, opzioni,null);
            SondaggioDTO s = SondaggioMapper.entityToDto(sondaggio1);

            //ArrayList<SondaggioDTO> sondaggiLista = SondaggioMapper.entityCollectionToDto(sd.selectAll());

            risposta = gson.toJson(s);
            exchange.sendResponseHeaders(200, 0);
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();

        } catch (Exception exc) {
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(400, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }
    }
}
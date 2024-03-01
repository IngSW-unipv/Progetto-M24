package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import it.unipv.ingsfw.opinione360.dto.UtenteDTO;
import it.unipv.ingsfw.opinione360.dto.UtenteMapper;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.AmministratoreDAO;
import it.unipv.ingsfw.opinione360.persistence.IUtenteDAO;
import it.unipv.ingsfw.opinione360.persistence.PersistenceFacade;
import it.unipv.ingsfw.opinione360.persistence.UtenteDAO;
import it.unipv.ingsfw.opinione360.persistence.exception.UserNotFoundException;

import java.io.*;
import java.sql.SQLException;

/**
 * Questa classe permette la gestione di un HttpExchange di login<br>
 * Accetta un HttpExchange che abbia nel body un oggetto {@link it.unipv.ingsfw.opinione360.model.Utente} rappresentato tramite Json.
 * @see HttpHandler
 */
public class LoginHandler implements HttpHandler {
    private String messaggio;
    private String risposta;

    /**
     * Costruttore
     */
    public LoginHandler(){
        messaggio = null;
        risposta = null;
    }

    /**
     * Implementazione del metodo dell'interfaccia HttpHandler che gestisce il login
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        PersistenceFacade pf = PersistenceFacade.getIstance();
        messaggio = null;
        try{
            InputStream in = exchange.getRequestBody();
            BufferedReader buffread = new BufferedReader(new InputStreamReader(in));
            messaggio = buffread.readLine();
            if(!messaggio.isBlank()){
                Utente utenteIn = gson.fromJson(messaggio, Utente.class);
                UtenteDTO utenteOut = UtenteMapper.entityToDto(pf.selectByUsrPw(utenteIn));
                risposta = gson.toJson(utenteOut);
                exchange.sendResponseHeaders(200, risposta.length());
            }
            else{
                risposta = "Dati mancanti.";
                exchange.sendResponseHeaders(400, risposta.length());
            }
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();

        } catch (UserNotFoundException exc){
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(404, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        } catch (Exception exc) {
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(500, 0);
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }
    }
}
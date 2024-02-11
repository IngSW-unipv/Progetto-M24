package it.unipv.ingsfw.opinione360.handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import it.unipv.ingsfw.opinione360.dto.UtenteDTO;
import it.unipv.ingsfw.opinione360.dto.UtenteMapper;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.IUtenteDAO;
import it.unipv.ingsfw.opinione360.persistence.UtenteDAO;
import it.unipv.ingsfw.opinione360.persistence.exception.UserNotFoundException;

import java.io.*;
import java.sql.SQLException;

/**
 * Questa classe permette la gestione di un HttpExchange di login
 * Accetta un HTTPExchange che abbia la segeuente struttura del body:<br>
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
     * Override del metodo dell'interfaccia HttpHandler che gestisce il login
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        IUtenteDAO ud = new UtenteDAO();
        messaggio = null;
        try{
            InputStream in = exchange.getRequestBody();
            BufferedReader buffread = new BufferedReader(new InputStreamReader(in));
            messaggio = buffread.readLine();
            Utente utenteIn = gson.fromJson(messaggio, Utente.class);
            if(utenteIn != null){
                 UtenteDTO utenteOut = UtenteMapper.entityToDto(ud.selectByUsrPw(utenteIn));
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
            exchange.sendResponseHeaders(401, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        } catch (SQLException exc) {
            risposta = exc.getMessage();
            exchange.sendResponseHeaders(500, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }
    }
}
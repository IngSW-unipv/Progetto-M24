package it.unipv.ingsfw.opinione360.server;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Filtro che preprocessa un HttpExchange per verificare se la richesta è di tipo POST
 */
public class PostMethodFilter extends Filter {
    /**
     * Override del metodo doFilter della classe {@link com.sun.net.httpserver.Filter}
     * @param exchange the {@code HttpExchange} to be filtered
     * @param chain the {@code Chain} which allows the next filter to be invoked
     * @throws IOException
     */
    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        if(exchange.getRequestMethod().equalsIgnoreCase("POST"))
            chain.doFilter(exchange);
        else{
            String risposta = "Richiesta errata. Attesa una richiesta di tipo POST";
            exchange.sendResponseHeaders(405, risposta.length());
            OutputStream out = exchange.getResponseBody();
            out.write(risposta.getBytes());
            exchange.close();
        }
    }

    /**
     * Override del metodo description della classe {@link com.sun.net.httpserver.Filter}
     * @return una descrizione del filtro
     */
    @Override
    public String description() {
        return "Filtro che controlla se la richiesta HTTP é di tipo POST";
    }
}

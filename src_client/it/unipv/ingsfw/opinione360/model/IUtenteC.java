package it.unipv.ingsfw.opinione360.model;

import java.util.UUID;

public interface IUtenteC {

    UUID getId();

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getEmail();

    void setEmail(String email);

    String getId_societario();

    void setId_societario(String id_societario);
}

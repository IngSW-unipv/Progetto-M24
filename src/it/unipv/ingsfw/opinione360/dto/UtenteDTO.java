package it.unipv.ingsfw.opinione360.dto;

import it.unipv.ingsfw.opinione360.model.Vetrina;

import java.util.UUID;

public class UtenteDTO {
    private UUID id;
    private String username;
    private String email;


    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

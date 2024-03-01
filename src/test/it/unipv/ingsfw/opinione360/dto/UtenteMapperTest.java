package test.it.unipv.ingsfw.opinione360.dto;

import it.unipv.ingsfw.opinione360.dto.UtenteDTO;
import it.unipv.ingsfw.opinione360.dto.UtenteMapper;
import it.unipv.ingsfw.opinione360.model.Amministratore;

import it.unipv.ingsfw.opinione360.model.Utente;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UtenteMapperTest {
    Utente user;
    Amministratore admin;
    ArrayList<Utente> au = new ArrayList<>();
    @Before
    public void init(){
        user = new Utente("TestU", "DV11", "testU@opinione360.it", "TEST");
        admin = new Amministratore("TestAmm", "DV12", "testAmm@opinione360.it", "TEST2");
        au.add(user);
        au.add(admin);
    }

    @Test
    public void entityToDtoTestOK1(){
        UtenteDTO utenteDto = UtenteMapper.entityToDto(user);
        assertNotNull(utenteDto);
    }

    @Test
    public void entityToDtoTestOK2(){
        UtenteDTO utenteDto = UtenteMapper.entityToDto(user);
        assertEquals(user.getId(), utenteDto.getId());
        assertEquals(user.getUsername(),utenteDto.getUsername());
        assertEquals(user.getId_societario(), utenteDto.getIdSocietario());
    }

    @Test
    public void entityToDtoTestOK3(){
        UtenteDTO utenteDto = UtenteMapper.entityToDto(user);
        assertEquals("it.unipv.ingsfw.opinione360.model.Utente", utenteDto.getType());
    }

    @Test
    public void entityToDtoTestKO(){
        UtenteDTO utenteDto = UtenteMapper.entityToDto(user);
        assertNotEquals("it.unipv.ingsfw.opinione360.model.Amministratore", utenteDto.getType());
    }

    @Test
    public void entityToDtoTestOK4(){
        UtenteDTO utenteDto = UtenteMapper.entityToDto(admin);
        assertEquals("it.unipv.ingsfw.opinione360.model.Amministratore", utenteDto.getType());
    }

    @Test
    public void entityToDtoTestKO2(){
        Utente fakeUser = new Utente();
        UtenteDTO utenteDto = UtenteMapper.entityToDto(fakeUser);
        assertNull(utenteDto.getUsername());
        assertNull(utenteDto.getIdSocietario());
    }

    @Test
    public void entityCollectionToDtoTestEq3(){
        ArrayList<UtenteDTO> aud = UtenteMapper.entityCollectionToDto(au);
        assertNotNull(aud.get(0));
        assertNotNull(aud.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> aud.get(2));
    }
}

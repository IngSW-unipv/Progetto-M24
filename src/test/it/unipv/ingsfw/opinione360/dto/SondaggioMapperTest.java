package test.it.unipv.ingsfw.opinione360.dto;

import it.unipv.ingsfw.opinione360.dto.SondaggioDTO;
import it.unipv.ingsfw.opinione360.dto.SondaggioMapper;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.Utente;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SondaggioMapperTest {
    Sondaggio sondaggio;
    ArrayList<Sondaggio> as = new ArrayList<>();
    @Before
    public void init(){
        Utente u1 = new Utente("Davide Valsecchi", "DV11");
        Utente u3 = new Utente("Tizio Caio", "password", "tiziocaio@nonso.bho", "TC1200802");
        ArrayList<Utente> v = new ArrayList<>();
        v.add(u1);
        v.add(u3);
        String[] opzioni = {"Michael Schumacher", "Lewis Hamilton", "Ayrton Senna", "Sebastian Vettel", "Alain Prost"};
        sondaggio = new Sondaggio("Miglior pilota di Formula 1?",v,v, opzioni,null);
        as.add(sondaggio);
        as.add(sondaggio);
    }

    @Test
    public void entityToDtoTestEq1(){
        SondaggioDTO s = SondaggioMapper.entityToDto(sondaggio);
        assertNotNull(s);
    }

     @Test
    public void entityToDtoTestEq2(){
        SondaggioDTO s = SondaggioMapper.entityToDto(sondaggio);
        assertEquals(sondaggio.getId(), s.getId());
        assertEquals(sondaggio.getQuesito(), s.getDescrizione());
        assertArrayEquals(sondaggio.getOpzioni(), s.getOpzioni());
        assertEquals(sondaggio.getVetrina(), s.getVetrina());
    }

    @Test
    public void entityCollectionToDtoTestEq3(){
        ArrayList<SondaggioDTO> s = SondaggioMapper.entityCollectionToDto(as);
        assertNotNull(s.get(0));
        assertNotNull(s.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> s.get(2));
    }
}

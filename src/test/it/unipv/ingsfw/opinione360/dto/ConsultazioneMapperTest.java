package test.it.unipv.ingsfw.opinione360.dto;

import it.unipv.ingsfw.opinione360.dto.ConsultazioneDTO;
import it.unipv.ingsfw.opinione360.dto.ConsultazioneMapper;
import it.unipv.ingsfw.opinione360.model.IConsultazione;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.Utente;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ConsultazioneMapperTest {
    Sondaggio sondaggio;
    ArrayList<IConsultazione> as = new ArrayList<>();
    @Before
    public void init(){
        Utente user1 = new Utente("Test1", "DV11");
        Utente user2 = new Utente("Test2", "DV12", "test2@opinione360.it", "TEST");
        ArrayList<Utente> v = new ArrayList<>();
        v.add(user1);
        v.add(user2);
        String[] opzioni = {"Michael Schumacher", "Lewis Hamilton", "Ayrton Senna", "Sebastian Vettel", "Alain Prost"};
        sondaggio = new Sondaggio("Miglior pilota di Formula 1?",v,v, opzioni,null);
        as.add(sondaggio);
        as.add(sondaggio);
    }

    @Test
    public void entityToDtoTestOK(){
        ConsultazioneDTO consultazioneDto = ConsultazioneMapper.entityToDto(sondaggio);
        assertNotNull(consultazioneDto);
    }

     @Test
    public void entityToDtoTestOK2(){
        ConsultazioneDTO consultazioneDto = ConsultazioneMapper.entityToDto(sondaggio);
        assertEquals(sondaggio.getId(), consultazioneDto.getId());
        assertEquals(sondaggio.getQuesito(), consultazioneDto.getDescrizione());
    }

    @Test
    public void entityToDtoTestKO(){
        ConsultazioneDTO consDto = ConsultazioneMapper.entityToDto(sondaggio);
        assertEquals("it.unipv.ingsfw.opinione360.model.Sondaggio", consDto.getType());
    }

    @Test
    public void entityCollectionToDtoTestEq3(){
        ArrayList<ConsultazioneDTO> asd = ConsultazioneMapper.entityCollectionToDto(as);
        assertNotNull(asd.get(0));
        assertNotNull(asd.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> asd.get(2));
    }
}

package it.unipv.ingsfw.opinione360.view.voto;

import java.util.UUID;

import javax.swing.JButton;

/**
 * Bottone che contiene l'informazione della consultazione a cui fa accedere
 * @see ConsultazioniFrame
 */
public class ConsultazioneBottone extends JButton {
	
	private UUID id;
	private String quesito;

	/**
	 * Costruttore parametrizzato
	 * @param id id della consultazione
	 * @param quesito quesito della consultazione
	 */
	public ConsultazioneBottone(UUID id, String quesito) {
		super();
		this.id = id;
		this.quesito = quesito;
		setText(quesito);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getQuesito() {
		return quesito;
	}

	public void setQuesito(String quesito) {
		this.quesito = quesito;
	}
	
}

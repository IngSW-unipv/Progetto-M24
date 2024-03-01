package it.unipv.ingsfw.opinione360.view.voto;

import javax.swing.JRadioButton;

import it.unipv.ingsfw.opinione360.model.OpzioneC;

/**
 * Bottone che contiene le informazioni del'opzione che si vuole scegliere
 */
public class OpzioneBottone extends JRadioButton {
	
	private OpzioneC opzione;

	/**
	 * Costruttore parametrizzato
	 * @param opzione opzione della consultazione
	 */
	public OpzioneBottone(OpzioneC opzione) {
		super();
		this.opzione = opzione;
		setText(opzione.getDescription());
	}

	public OpzioneC getOpzione() {
		return opzione;
	}

	public void setOpzione(OpzioneC opzione) {
		this.opzione = opzione;
	}
	
}

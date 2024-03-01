package it.unipv.ingsfw.opinione360.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public class ContenutoTestoC implements IContenutoC {
	
	private String data;
	private UUID id;
	private final String extension = "TXT";
	
	public ContenutoTestoC() {
		this.id = UUID.randomUUID();
	}
	
	@Override
	public IContenutoC fromFile(File contenuto) throws IOException {
		FileReader fr = new FileReader(contenuto.getPath());
		BufferedReader br = new BufferedReader(fr);
		String riga = br.readLine();
		data = new String(riga);
		while(riga != null) {
			data.concat(riga);
			riga = br.readLine();
		}
		fr.close();
		return new ContenutoTestoC(data);
	}
	
	public ContenutoTestoC(UUID id) {
		this.id = id;
	}
	public ContenutoTestoC(String data) {
		this.id = UUID.randomUUID();
		this.data = data;
	}
	public ContenutoTestoC(UUID id, String data) {
		this.id = id;
		this.data = data;
	}

	@Override
	public byte[] getData() {
		return this.data.getBytes();
	}

	@Override
	public UUID getId() {
		return this.id;
	}

	public void setData(char[] data) {
		this.data = data.toString();
	}
	@Override
	public void setData(byte[] data) {
		this.data = data.toString();
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return this.data;
	}
	@Override
	public String getExtension() {
		return this.extension;
	}	
	
}

package it.unipv.ingsfw.opinione360.model;

import java.util.UUID;

public class ContenutoTesto implements IContenuto {
	private String data;
	private UUID id;
	private String extension = ".txt";
	
	public ContenutoTesto() {
		this.id = UUID.randomUUID();
	}
	public ContenutoTesto(UUID id) {
		this.id = id;
	}
	public ContenutoTesto(String data) {
		this.id = UUID.randomUUID();
		this.data=data;
	}
	public ContenutoTesto(UUID id, String data) {
		this.id = id;
		this.data=data;
	}

	@Override
	public char[] getData() {
		return this.data.toCharArray();
	}

	@Override
	public UUID getId() {
		return this.id;
	}
	@Override
	public void setData(char[] data) {
		this.data=data.toString();
	}
	
	public void setData(String data) {
		this.data=data;
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

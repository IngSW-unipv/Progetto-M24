package it.unipv.ingsfw.opinione360.model;

import java.util.UUID;

public class ContenutoTesto implements IContenuto {
	private String data;
	private UUID id;
	private final String extension = "TXT";
	
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
	public byte[] getData() {
		return this.data.getBytes();
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
	
	@Override
	public boolean equals(Object obj) {
		ContenutoTesto c = (ContenutoTesto) obj;
		return this.id.equals(c.getId());
	}
	
}

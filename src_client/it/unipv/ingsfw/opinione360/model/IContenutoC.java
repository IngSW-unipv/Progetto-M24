package it.unipv.ingsfw.opinione360.model;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public interface IContenutoC {

	byte[] getData();
	UUID getId();
	void setData(byte[] data);
	String getExtension();
	IContenutoC fromFile(File contenuto) throws IOException;
	
}

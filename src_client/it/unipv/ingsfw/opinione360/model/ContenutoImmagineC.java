package it.unipv.ingsfw.opinione360.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

public class ContenutoImmagineC implements IContenutoC {
	
	private BufferedImage data;
	private UUID id;
	private final String extension = "PNG";
	
	public ContenutoImmagineC() {
		this.id = UUID.randomUUID();
	}
	
	@Override
	public IContenutoC fromFile(File contenuto) throws IOException {
		FileInputStream fr = new FileInputStream(contenuto.getPath());
		byte b = (byte) fr.read();
		byte[] bytes = new byte[fr.available()];
		int i = 0;
		while (b != -1) {
			bytes[i] = b;
			i++;
		}
		data = ImageIO.read(new ByteArrayInputStream(bytes));
		fr.close();
		return new ContenutoImmagineC(data);
	}
	
	public ContenutoImmagineC(UUID id) {
		this.id = id;
	}
	public ContenutoImmagineC(BufferedImage data) {
		this.id = UUID.randomUUID();
		this.data = data;
	}
	public ContenutoImmagineC(UUID id, BufferedImage data) {
		this.id = id;
		this.data = data;
	}

	@Override
	public byte[] getData()  {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(data, "png", baos); 
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return baos.toByteArray();
	}

	@Override
	public UUID getId() {
		return this.id;
	}
	@Override
	public void setData(byte[] data) {
		try {
			this.data = ImageIO.read(new ByteArrayInputStream(data));
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void setData(BufferedImage data) {
		this.data = data;
	}
	
	public BufferedImage toImage() {
		return this.data;
	}
	@Override
	public String getExtension() {
		return this.extension;
	}	
	
}

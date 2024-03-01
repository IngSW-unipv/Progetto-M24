package it.unipv.ingsfw.opinione360.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

public class ContenutoImmagine implements IContenuto {

	private BufferedImage data;
	private UUID id;
	private final String extension = "PNG";

	public ContenutoImmagine() {
		this.id = UUID.randomUUID();
	}

	public IContenuto fromFile(File contenuto) throws IOException {
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
		return new ContenutoImmagine(data);
	}

	public ContenutoImmagine(UUID id) {
		this.id = id;
	}
	public ContenutoImmagine(BufferedImage data) {
		this.id = UUID.randomUUID();
		this.data = data;
	}
	public ContenutoImmagine(UUID id, BufferedImage data) {
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
	public void setData(char[] data) {

	}

	@Override
	public UUID getId() {
		return this.id;
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

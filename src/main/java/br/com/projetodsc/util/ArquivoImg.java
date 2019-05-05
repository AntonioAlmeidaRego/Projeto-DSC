package br.com.projetodsc.util;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ArquivoImg implements Arquivo{
	private File file = null;
	private BufferedImage image = null;
	private int width;
	private int height;
	private String typeImg;
	
	 
	public ArquivoImg(int width, int height, String typeImg) {
		this.width = width;
		this.height = height;
		this.typeImg = typeImg;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTypeImg() {
		return typeImg;
	}

	public void setTypeImg(String typeImg) {
		this.typeImg = typeImg;
	}

	@Override
	public boolean creatFile(String urlOrigem) throws IOException {
		file = new File(urlOrigem); //image file path
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        image = ImageIO.read(file);
		if(image != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean writeFile(String urlDestino) throws IOException{
		file = new File(urlDestino);  //output file path
		return ImageIO.write(image, typeImg, file);
	}
	
	

}

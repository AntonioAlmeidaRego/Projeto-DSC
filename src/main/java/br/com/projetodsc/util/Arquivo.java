package br.com.projetodsc.util;

import java.io.IOException;

public interface Arquivo {
	public boolean creatFile(String urlOrigem) throws IOException;
	public boolean writeFile(String urlDestino) throws IOException;
	public void reloadFile(String urlDestino) throws IOException;
}

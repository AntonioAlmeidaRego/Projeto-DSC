package br.com.projetodsc.util;

import org.springframework.web.multipart.MultipartFile;

public interface SaveImg<T> {
	public void saveImg(MultipartFile file, T obj, T aux);
}

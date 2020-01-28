package br.com.projetodsc.util;

import java.util.List;

public interface EntityService<T extends Object> {
	public void save(T entity);
	public void deleteById(Long id);
	public T getOne(Long id);
	public List<T> findAll();
}

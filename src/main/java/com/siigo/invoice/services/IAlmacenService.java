package com.siigo.invoice.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.siigo.invoice.model.Warehouse;
import com.siigo.invoice.util.search.SearchResult;

	public interface IAlmacenService {

	public List<Warehouse> findAll();
	
	public SearchResult<Warehouse> findAll(Pageable pageable);
	
	public Warehouse findById(UUID id);
	
	public Warehouse save(Warehouse almacen);
	
	public void delete(UUID id);
	
}

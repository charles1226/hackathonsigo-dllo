package com.siigo.invoice.services;

import java.util.List;
import java.util.UUID;

import com.siigo.invoice.model.Producto;

public interface IProductoService {

	public Producto findById(UUID id);
	
	public Producto save(Producto producto);
	
	public List<Producto> findByNombre(String term);
}

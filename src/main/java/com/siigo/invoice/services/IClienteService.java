package com.siigo.invoice.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.siigo.invoice.model.Cliente;
import com.siigo.invoice.model.Factura;
import com.siigo.invoice.model.Producto;
import com.siigo.invoice.model.Region;
import com.siigo.invoice.util.search.SearchResult;

	public interface IClienteService {

	public List<Cliente> findAll();
	
	public SearchResult<Cliente> findAll(Pageable pageable);
	
	public Cliente findById(UUID id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(UUID id);
	
	public List<Region> findAllRegiones();
	
	public Factura findFacturaById(UUID id);
	
	public Factura saveFactura(Factura factura);
	
	public void deleteFacturaById(UUID id);
	
	public List<Producto> findProductoByNombre(String term);
	
	public void verificarStock(Factura factura);
}

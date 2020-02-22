package com.siigo.invoice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siigo.invoice.dao.IClienteDAO;
import com.siigo.invoice.dao.IFacturaDao;
import com.siigo.invoice.dao.IProductoDao;
import com.siigo.invoice.model.Cliente;
import com.siigo.invoice.model.Factura;
import com.siigo.invoice.model.Producto;
import com.siigo.invoice.model.Region;
import com.siigo.invoice.util.search.SearchResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

	private final IClienteDAO clienteDao;
	private final IFacturaDao facturaDao;
	private final IProductoDao productoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public SearchResult<Cliente> findAll(Pageable pageable) {
		List<Cliente> results = new ArrayList<>();
		long resultsCount = clienteDao.count();
		if(resultsCount > 0) {			
			results = clienteDao.findAll(pageable).toList();
		}
		return new SearchResult<Cliente>(resultsCount, results);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(UUID id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(UUID id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones() {
		return clienteDao.findAllRegiones();
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(UUID id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Factura saveFactura(Factura factura) {
		return facturaDao.save(factura);
	}

	@Override
	@Transactional
	public void deleteFacturaById(UUID id) {
		facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findProductoByNombre(String term) {
		return productoDao.findByNombreContainingIgnoreCase(term);
	}

}

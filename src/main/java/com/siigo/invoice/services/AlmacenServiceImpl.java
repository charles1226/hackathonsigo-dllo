package com.siigo.invoice.services;

import static com.siigo.invoice.util.validation.FormsValidationUtils.assertNotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siigo.invoice.dao.IAlmacenDao;
import com.siigo.invoice.dao.IFacturaDao;
import com.siigo.invoice.model.Factura;
import com.siigo.invoice.model.Warehouse;
import com.siigo.invoice.util.exception.ResourceNotFoundException;
import com.siigo.invoice.util.exception.ValidationException;
import com.siigo.invoice.util.search.SearchResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlmacenServiceImpl implements IAlmacenService {

	private final IAlmacenDao almacenDao;
	private final IFacturaDao facturaDao;
	private final IClienteService clienteService;

	@Override
	@Transactional(readOnly = true)
	public List<Warehouse> findAll() {
		return (List<Warehouse>) almacenDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public SearchResult<Warehouse> findAll(Pageable pageable) {
		List<Warehouse> results = new ArrayList<>();
		long resultsCount = almacenDao.count();
		if(resultsCount > 0) {			
			results = almacenDao.findAll(pageable).toList();
		}
		return new SearchResult<Warehouse>(resultsCount, results);
	}

	@Override
	@Transactional(readOnly = true)
	public Warehouse findById(UUID id) {
		return almacenDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Almacen", "id", id.toString()));
	}

	@Override
	@Transactional
	public Warehouse save(Warehouse almacen) {
		validarAlmacen(almacen);
		Warehouse warehouse = almacenDao.save(almacen);
		desencolarFacturas();
		return warehouse;
	}
	
	private void desencolarFacturas() {
		List<Factura> facturasPendientes = facturaDao.findByEstadoOrderByCreateAtAsc(-1);
		facturasPendientes.stream().forEach(factura -> {
			clienteService.verificarStock(factura);			
		});
	}

	private void validarAlmacen(Warehouse almacen) {
		JSONObject validationErrors = new JSONObject();
		assertNotBlank(validationErrors, "nombre", almacen.getNombre(), "El nombre no puede ser vacío");
		assertNotBlank(validationErrors, "direccion", almacen.getNombre(), "La dirección no puede ser vacía");
		if(!validationErrors.isEmpty())
			throw new ValidationException(validationErrors.toString());
	}

	@Override
	@Transactional
	public void delete(UUID id) {
		almacenDao.deleteById(id);
	}

}

package com.siigo.invoice.services;

import static com.siigo.invoice.util.validation.FormsValidationUtils.assertNotBlank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siigo.invoice.dao.IClienteDAO;
import com.siigo.invoice.dao.IFacturaDao;
import com.siigo.invoice.dao.IItemDao;
import com.siigo.invoice.dao.IProductoDao;
import com.siigo.invoice.model.Cliente;
import com.siigo.invoice.model.Factura;
import com.siigo.invoice.model.ItemWarehouse;
import com.siigo.invoice.model.Producto;
import com.siigo.invoice.model.Region;
import com.siigo.invoice.util.exception.ResourceNotFoundException;
import com.siigo.invoice.util.exception.ValidationException;
import com.siigo.invoice.util.search.SearchResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

	private final IClienteDAO clienteDao;
	private final IFacturaDao facturaDao;
	private final IProductoDao productoDao;
	private final IItemDao itemDao;

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
		return clienteDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id.toString()));
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		validarCliente(cliente);
		return clienteDao.save(cliente);
	}

	private void validarCliente(Cliente cliente) {
		JSONObject validationErrors = new JSONObject();
		assertNotBlank(validationErrors, "nombre", cliente.getNombre(), "El nombre no puede ser vacío");
		assertNotBlank(validationErrors, "appellido", cliente.getNombre(), "El apellido no puede ser vacío");
		assertNotBlank(validationErrors, "email", cliente.getNombre(), "El correo electrónico no puede ser vacío");
		if(!validationErrors.isEmpty())
			throw new ValidationException(validationErrors.toString());
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
		verificarStock(factura);
		return facturaDao.save(factura);
	}

	@Override
	public void verificarStock(Factura factura) {
		List<Producto> productosIds = new ArrayList<Producto>();
		Map<UUID, Integer> productosCantidades = new HashMap<UUID, Integer>();
		factura.getItems().stream().forEach(item -> {
			productosIds.add(item.getProducto());
			productosCantidades.put(item.getProducto().getId(), item.getCantidad());
		});
		Map<UUID, Integer>  productosFactura = new HashMap<UUID, Integer>(productosCantidades);
		List<ItemWarehouse> products = itemDao.retrieveByProducts(productosIds);
		List<ItemWarehouse> productosDisponibles = new ArrayList<ItemWarehouse>();
		products.stream().forEach(producto -> {
			if(productosCantidades.get(producto.getProducto().getId()) != null && producto.getCantidad() >= productosCantidades.get(producto.getProducto().getId())) {
				productosCantidades.remove(producto.getProducto().getId());
				productosDisponibles.add(producto);
			}
		});
		if(productosDisponibles.size() != productosFactura.size()) {
			factura.setEstado(-1);
		} else {
			factura.setEstado(1);
			productosDisponibles.stream().forEach(producto -> {
				producto.setCantidad(producto.getCantidad() - productosFactura.get(producto.getProducto().getId()));
				itemDao.save(producto);
			});
		}
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

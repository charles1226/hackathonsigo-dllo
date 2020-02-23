package com.siigo.invoice.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siigo.invoice.dao.IProductoDao;
import com.siigo.invoice.model.Producto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements IProductoService{
	
	private final IProductoDao productoDao;
	
	@Override
	public Producto findById(UUID id) {
		return productoDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	public List<Producto> findByNombre(String nombre) {
		return productoDao.findByNombre(nombre);
	}

}

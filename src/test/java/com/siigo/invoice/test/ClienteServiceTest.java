package com.siigo.invoice.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.siigo.invoice.dao.IClienteDAO;
import com.siigo.invoice.dao.IFacturaDao;
import com.siigo.invoice.dao.IProductoDao;
import com.siigo.invoice.model.Cliente;
import com.siigo.invoice.model.Factura;
import com.siigo.invoice.model.Producto;
import com.siigo.invoice.model.Region;
import com.siigo.invoice.services.ClienteServiceImpl;

public class ClienteServiceTest {

	@Spy
	IProductoDao iProductoDao;
	
	@Spy
	IClienteDAO iclienteDao;
	
	@Spy
	IFacturaDao iFacturaDao;
	
	@InjectMocks
	ClienteServiceImpl clienteServiceImpl;
	
	@Before
	public void init(){
	 MockitoAnnotations.initMocks(this); 
	}
	
	@Test
	public void createTest() {
		Cliente cliente = new Cliente();
		cliente.setNombre("Charles");
		cliente.setApellido("prueba");
		cliente.setEmail("nessjcharles@gmail.com");
		
		Cliente clienteAlmacenado = objetoCliente();
		Mockito.when(iclienteDao.save(cliente)).thenReturn(cliente);
		cliente = clienteServiceImpl.save(cliente);
		assertEquals("Charles", clienteAlmacenado.getNombre());
	}
	
	@Test
	public void findAllTest() {

		UUID id = new UUID(0, 1L);
		Mockito.when(iclienteDao.findById(id)).thenReturn(Optional.of(objetoCliente()));
		Cliente cliente = clienteServiceImpl.findById(id);
		assertEquals("nessjcharles@gmail.com",cliente.getEmail());
		
	}
	
	@Test
	public void findAllRegionesTest() {
		List<Region> lista = new ArrayList<Region>();
		Region regionUno = new Region();
		lista.add(regionUno );
		
		Mockito.when(iclienteDao.findAllRegiones()).thenReturn(lista);
		List<Region> regiones = clienteServiceImpl.findAllRegiones();
		assertEquals(1, regiones.size());
	}
	
	@Test
	public void findFacturaByIdTest() {
		UUID id = new UUID(0, 1L);
		
		Mockito.when(iFacturaDao.findById(id)).thenReturn(Optional.of(objetoFactura()));
		Factura factura = clienteServiceImpl.findFacturaById(id);
		assertEquals(factura.getObservacion(),"facturaPrueba");
	}
	
	@Ignore
	@Test
	public void facturaSaveTest() {
		Factura factura = new Factura();
		
		Mockito.when(iFacturaDao.save(factura)).thenReturn(objetoFactura());
		Factura facturaF = clienteServiceImpl.saveFactura(factura);
		assertEquals(facturaF.getDescripcion(),"facturaUno");
	}
	
	@Ignore
	@Test
	public void productoPorNombre() {
		String nombre = "productoUno";
		List<Producto> lista = new ArrayList<Producto>();
		Producto producto = new Producto();
		lista.add(producto );
		
		Mockito.when(iProductoDao.findByNombreStartingWithIgnoreCase(nombre)).thenReturn(lista);
		List<Producto> listaf = clienteServiceImpl.findProductoByNombre(nombre);
		assertEquals(listaf.size(),1);
	}
	
	public Cliente objetoCliente() {
		Cliente cliente = new Cliente();
		cliente.setNombre("Charles");
		cliente.setEmail("nessjcharles@gmail.com");
		return cliente;
	}
	
	public Factura objetoFactura() {
		Factura factura = new Factura();
		factura.setDescripcion("facturaUno");
		factura.setObservacion("facturaPrueba");
		return factura;
	}
}

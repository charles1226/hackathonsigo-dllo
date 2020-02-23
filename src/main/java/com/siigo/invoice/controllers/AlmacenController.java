package com.siigo.invoice.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.siigo.invoice.model.Producto;
import com.siigo.invoice.model.Warehouse;
import com.siigo.invoice.services.IAlmacenService;
import com.siigo.invoice.services.IClienteService;
import com.siigo.invoice.util.search.SearchResult;
import com.siigo.invoice.util.search.SearcherDataDTO;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AlmacenController {

	private final IClienteService clienteService;
	private final IAlmacenService almacenService;
	
	@GetMapping(value = "/almacenes")
	public List<Warehouse> index() {
		return almacenService.findAll();
	}
	
	@GetMapping(value = "/almacenes/page/{page}")
	public ResponseEntity<SearcherDataDTO<Warehouse>> page(@PathVariable(name = "page") Long page) {
		SearchResult<Warehouse> almacenessResult = almacenService.findAll(PageRequest.of(page.intValue(), 5));
		return new ResponseEntity<SearcherDataDTO<Warehouse>>(new SearcherDataDTO<Warehouse>(page, almacenessResult.getResultsCount(), almacenessResult.getResult()), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping(value = "/almacenes/{id}")
	public Warehouse show(@PathVariable UUID id) {
		return almacenService.findById(id);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/almacenes")
	@ResponseStatus(HttpStatus.CREATED)
	public Warehouse create(@Valid @RequestBody Warehouse almacen) {
		return almacenService.save(almacen);
	}
	
	@Secured("ROLE_ADMIN")
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/almacenes")
	public Warehouse update(@RequestBody Warehouse almacen){
		return almacenService.save(almacen);
		
	}
	
	@Secured("ROLE_ADMIN")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/almacenes/{id}")
	public void delete(@PathVariable UUID id) {
		almacenService.delete(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/almacenes/filtrar-productos/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Producto> filtrarProductos(@PathVariable String term){
		return clienteService.findProductoByNombre(term);
	}
}

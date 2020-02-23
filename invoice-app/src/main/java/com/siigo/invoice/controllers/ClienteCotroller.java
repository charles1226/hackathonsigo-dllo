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

import com.siigo.invoice.model.Cliente;
import com.siigo.invoice.model.Region;
import com.siigo.invoice.services.IClienteService;
import com.siigo.invoice.util.search.SearchResult;
import com.siigo.invoice.util.search.SearcherDataDTO;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class ClienteCotroller {

	private final IClienteService clienteService;
	
	@GetMapping(value = "/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}
	
	@GetMapping(value = "/clientes/page/{page}")
	public ResponseEntity<SearcherDataDTO<Cliente>> page(@PathVariable(name = "page") Long page) {
		SearchResult<Cliente> clientesResult = clienteService.findAll(PageRequest.of(page.intValue(), 5));
		return new ResponseEntity<SearcherDataDTO<Cliente>>(new SearcherDataDTO<Cliente>(page, clientesResult.getResultsCount(), clientesResult.getResult()), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping(value = "/clientes/{id}")
	public Cliente show(@PathVariable UUID id) {
		return clienteService.findById(id);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@Valid @RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	@Secured("ROLE_ADMIN")
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/clientes")
	public Cliente update(@RequestBody Cliente cliente){
		return clienteService.save(cliente);
		
	}
	
	@Secured("ROLE_ADMIN")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/clientes/{id}")
	public void delete(@PathVariable UUID id) {
		clienteService.delete(id);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
		return clienteService.findAllRegiones();
	}
}

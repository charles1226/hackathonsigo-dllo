package com.siigo.invoice.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siigo.invoice.model.Cliente;
import com.siigo.invoice.model.Region;

public interface IClienteDAO extends JpaRepository<Cliente, UUID> {

	@Query("from Region")
	public List<Region> findAllRegiones();

}

package com.siigo.invoice.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.siigo.invoice.model.Factura;

public interface IFacturaDao extends CrudRepository<Factura, UUID>{

	List<Factura> findByEstadoOrderByCreateAtAsc(Integer estado);

}

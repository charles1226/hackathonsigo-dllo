package com.siigo.invoice.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.siigo.invoice.model.Factura;

public interface IFacturaDao extends CrudRepository<Factura, UUID>{

}

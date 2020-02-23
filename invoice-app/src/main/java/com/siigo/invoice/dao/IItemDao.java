package com.siigo.invoice.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.siigo.invoice.model.ItemWarehouse;
import com.siigo.invoice.model.Producto;

public interface IItemDao extends CrudRepository<ItemWarehouse, UUID>{

	@Query("SELECT iw FROM ItemWarehouse iw WHERE iw.producto in (:idProducto)")
	List<ItemWarehouse> retrieveByProducts(@Param("idProducto") List<Producto> idProducto);
	
}

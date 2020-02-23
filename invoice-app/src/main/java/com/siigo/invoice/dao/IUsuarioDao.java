package com.siigo.invoice.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.siigo.invoice.model.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, UUID>{
	
	public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario findByUsername2(String username);

}

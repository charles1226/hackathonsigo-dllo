package com.siigo.invoice.services;

import com.siigo.invoice.model.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
}

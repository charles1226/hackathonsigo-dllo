package com.siigo.invoice.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siigo.invoice.model.Warehouse;

public interface IAlmacenDao extends JpaRepository<Warehouse, UUID> {

}

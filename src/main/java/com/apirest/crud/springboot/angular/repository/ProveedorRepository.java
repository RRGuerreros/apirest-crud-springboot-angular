package com.apirest.crud.springboot.angular.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.crud.springboot.angular.models.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

}

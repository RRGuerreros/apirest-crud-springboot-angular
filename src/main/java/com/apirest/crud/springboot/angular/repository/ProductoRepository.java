package com.apirest.crud.springboot.angular.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.crud.springboot.angular.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	

}

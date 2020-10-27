package com.apirest.crud.springboot.angular.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.crud.springboot.angular.models.Proveedor;
import com.apirest.crud.springboot.angular.services.CRUDService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rest/v1")
public class ProveedorRestController {

	@Autowired
	@Qualifier("proveedorServiceImpl")
	private CRUDService<Proveedor> proveedorCrudService;
	
	@RequestMapping( value = "/proveedores", method = RequestMethod.GET )
	public ResponseEntity<?> findAll(){
		return proveedorCrudService.findAll();
	}
}

package com.apirest.crud.springboot.angular.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.crud.springboot.angular.models.Producto;
import com.apirest.crud.springboot.angular.services.CRUDService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rest/v1")
public class ProductoRestController {

	@Autowired
	@Qualifier("productoServiceImpl")
	private CRUDService<Producto> productoCrudService;
	
	@RequestMapping( value = "/productos", method = RequestMethod.POST )
	public ResponseEntity<?> save( @RequestBody Producto producto ){
		return productoCrudService.save(producto);
	}
	
	@RequestMapping( value = "/productos", method = RequestMethod.PUT )
	public ResponseEntity<?> update( @RequestBody Producto producto ){
		return productoCrudService.update(producto);
	}
	
	@RequestMapping( value = "/productos/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<?> delete( @PathVariable int id ){
		return productoCrudService.deleteById(id);
	}
	
	@RequestMapping( value = "/productos/{id}", method = RequestMethod.GET )
	public ResponseEntity<?> findById( @PathVariable int id ){
		return productoCrudService.findById(id);
	}
	
	@RequestMapping( value = "/productos", method = RequestMethod.GET )
	public ResponseEntity<?> findAll(){
		return productoCrudService.findAll();
	}
	
}

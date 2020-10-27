package com.apirest.crud.springboot.angular.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apirest.crud.springboot.angular.models.Producto;
import com.apirest.crud.springboot.angular.models.Proveedor;
import com.apirest.crud.springboot.angular.repository.ProveedorRepository;
import com.apirest.crud.springboot.angular.services.CRUDService;
import com.google.gson.Gson;

@Service
public class ProveedorServiceImpl implements CRUDService<Proveedor> {

	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@Override
	public ResponseEntity<?> findAll() {
		
		Map<String, Object> body = new HashMap<String, Object>();
		
		try {
			
			List<Proveedor> proveedores = proveedorRepository.findAll();
			
			if( proveedores.isEmpty() ) {
				
				body.put("mensaje", "No hay registros grabados en la base de datos");
				body.put("proveedores", proveedores);
				
				return new ResponseEntity<String>(new Gson().toJson(body), HttpStatus.OK );
				
			} else {
				
				body.put("proveedores", proveedores);
				
				return new ResponseEntity<String>(new Gson().toJson(body), HttpStatus.OK );
			}
			
		} catch (Exception e) {
			
			body.put("mensaje", "Hubo un error -> " +e.getMessage());
			
			return new ResponseEntity<String>(new Gson().toJson(body), HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}

	@Override
	public ResponseEntity<?> deleteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResponseEntity<?> save(Proveedor objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> update(Proveedor objeto) {
		// TODO Auto-generated method stub
		return null;
	}
}

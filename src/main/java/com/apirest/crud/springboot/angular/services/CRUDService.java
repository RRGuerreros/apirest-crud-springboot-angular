package com.apirest.crud.springboot.angular.services;

import org.springframework.http.ResponseEntity;

public interface CRUDService<T> {
	
	ResponseEntity<?> save( T objeto );
	ResponseEntity<?> update( T objeto );
	ResponseEntity<?> findAll();
	ResponseEntity<?> deleteById(int id);
	ResponseEntity<?> findById(int id);

}

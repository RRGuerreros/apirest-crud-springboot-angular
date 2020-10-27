package com.apirest.crud.springboot.angular.services.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apirest.crud.springboot.angular.models.Costo;
import com.apirest.crud.springboot.angular.models.Producto;
import com.apirest.crud.springboot.angular.repository.ProductoRepository;
import com.apirest.crud.springboot.angular.repository.ProveedorRepository;
import com.apirest.crud.springboot.angular.services.CRUDService;
import com.google.gson.Gson;

@Service
public class ProductoServiceImpl implements CRUDService<Producto> {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public ResponseEntity<?> save(Producto producto) {
		
		Map<String, Object> body = new HashMap<String, Object>();
		
		try {
			
			if( producto.cambioFoto() ) {
				
				String base64String = producto.getFoto();
				
				byte[] decodedBytes = Base64.getDecoder().decode(base64String);
				
				String uniqueFileName = UUID.randomUUID().toString();
				
				producto.setFoto(uniqueFileName);
				
				Path rootAbsolutPath = Paths.get("uploads").resolve(uniqueFileName+"."+producto.getImageType()).toAbsolutePath();
							
				log.info("la ruta absoluta del archivo -> " + rootAbsolutPath);
				
				FileUtils.writeByteArrayToFile( new File(Paths.get("uploads").resolve(uniqueFileName+".jpg").toAbsolutePath().toString()), decodedBytes);
				
			}			
			
			List<Costo> costos = producto.getCostos();
			
			for (Costo costo : costos) {
				costo.setProducto(producto);
			}			
			
			productoRepository.save(producto);
			
			body.put("mensaje", "Se registró correctamente");
			
			return new ResponseEntity<String>( new Gson().toJson(body), HttpStatus.CREATED );
			
		} catch (Exception e) {
			
			body.put("mensaje", "Hubo un error -> " +e.getMessage());
			
			e.printStackTrace();
			
			return new ResponseEntity<String>( new Gson().toJson(body), HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}

	@Override
	public ResponseEntity<?> update(Producto productoRequest ) {
		
		Map<String, Object> body = new HashMap<String, Object>();
		
		try {			
			
			Producto producto = productoRepository.findById(productoRequest.getId()).orElse(null);
			
			if( productoRequest.cambioFoto() ) {
				
				try {
					
					Path rootAbsolutPath = Paths.get("uploads").toAbsolutePath();
					
					log.info("Editando el producto");
					log.info("rootAbsolutPath: " + rootAbsolutPath.toUri().toString());
					
					log.info("Ruta de la imagen: " + rootAbsolutPath.toString().concat("/").concat(producto.getFoto()).concat(".").concat(producto.getImageType()) );
					
					File fileImage = new File(rootAbsolutPath.toString().concat("/").concat(producto.getFoto()).concat(".").concat(producto.getImageType()));
					
					if( fileImage.exists() ) {
						if( fileImage.delete() ) {
							log.info("la imagen del producto fue eliminada con éxito.");
						}
					}
					
					String base64String = productoRequest.getFoto();
					
					byte[] decodedBytes = Base64.getDecoder().decode(base64String);
					
					String uniqueFileName = UUID.randomUUID().toString();
					
					productoRequest.setFoto(uniqueFileName);
					
					Path rootAbsolutPathNew = Paths.get("uploads").resolve(uniqueFileName.concat(".")+producto.getImageType()).toAbsolutePath();
					
					log.info("la ruta absoluta del archivo nuevo -> " + rootAbsolutPathNew);
					
					FileUtils.writeByteArrayToFile( rootAbsolutPathNew.toFile(), decodedBytes);
					
				} catch (Exception e) {
					
					log.info("Hubo un error interno al eliminar o crear la nueva imagen del producto");
					log.info("Error: -> " + e.getMessage() );
				}
				
			} else {
				
				productoRequest.setFoto(producto.getFoto());
			}
			
			List<Costo> costos = productoRequest.getCostos();
			
			for (Costo costo : costos) {
				
				if( costo.getId() == 0 ) {					
					costo.setProducto(productoRequest);					
				} 	
			}
			
			productoRepository.save(productoRequest);
			
			body.put("mensaje", "Se guardó correctamente");
			
			return new ResponseEntity<String>(new Gson().toJson(body), HttpStatus.OK );
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			body.put("mensaje", "Hubo un error -> " +e.getMessage());
			
			return new ResponseEntity<String>(new Gson().toJson(body), HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}

	@Override
	public ResponseEntity<?> findAll() {
		
		Map<String, Object> body = new HashMap<String, Object>();
		
		try {
			
			List<Producto> productos = productoRepository.findAll();
			
			if( productos.isEmpty() ) {
				
				body.put("mensaje", "No hay registros grabados en la base de datos");
				body.put("productos", productos);
				
				return new ResponseEntity<String>(new Gson().toJson(body), HttpStatus.OK );
				
			} else {
				
				body.put("productos", productos);
				
				return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK );
			}
			
		} catch (Exception e) {
			
			body.put("mensaje", "Hubo un error -> " +e.getMessage());
			
			e.printStackTrace();
			
			return new ResponseEntity<Map<String, Object>>(body,HttpStatus.INTERNAL_SERVER_ERROR );
		}		
	}

	@Override
	public ResponseEntity<?> deleteById(int id) {
		
		Map<String, Object> body = new HashMap<String, Object>();
		
		try {
			
			productoRepository.deleteById(id);
			
			body.put("mensaje", "Se elminó correctamente");
			
			return new ResponseEntity<String>(new Gson().toJson(body), HttpStatus.OK );
			
		} catch (Exception e) {
			
			body.put("mensaje", "Hubo un error -> " +e.getMessage());
			
			return new ResponseEntity<String>(new Gson().toJson(body), HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}

	@Override
	public ResponseEntity<?> findById(int id) {
		
		Map<String, Object> body = new HashMap<String, Object>();
		
		try {
			
			Producto producto = productoRepository.findById(id).orElse(null);
			
			if( producto == null ) {
				
				body.put("mensaje", "No se encontró el producto solicitado");
				
				return new ResponseEntity<Map<String, Object>>(body, HttpStatus.NOT_FOUND );
				
			} else {
				
				producto.getFechaVencimiento();
				
				if( producto.getFoto() != null && !producto.getFoto().equals("") ) {
					
					try {
						
						Path rootAbsolutPath = Paths.get("uploads").resolve(producto.getFoto().concat(".".concat(producto.getImageType()))).toAbsolutePath();
						
						log.info("la imagen del producto es -> " + rootAbsolutPath );
						
						byte[] fileContent = FileUtils.readFileToByteArray(rootAbsolutPath.toFile());
						
						String encoded = Base64.getEncoder().encodeToString(fileContent);
						
						producto.setFoto(encoded);
						
					} catch (Exception e) {
						
						log.info("Error: No pudo encontrar o leer la imagen guardada del producto");
						
						producto.setFoto(null);
					}
				}
				
				body.put("producto", producto );
				
				return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK );
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			body.put("mensaje", "Hubo un error -> " +e.getMessage());
			
			return new ResponseEntity<Map<String, Object>>(body, HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}

	
}

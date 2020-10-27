package com.apirest.crud.springboot.angular.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table( name = "tb_producto" )
public class Producto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nombre;
	private String marca;
	private String clasificacion;
	private String familia;
	private String categoria;
	private int stockInicial;
	private String fechaCreacion;
	private int stockMinimo;
	
	private boolean perecedor;
	private boolean venta;
	private boolean compra;
	private boolean obsequio;
	private String fechaVencimiento;
	private String comentario;

	private List<Costo> costos;
	
	
	private String foto;
	private String imageType;
	private boolean cambioFoto;
	
	public Producto() {
		this.costos = new ArrayList<Costo>();
	} 

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Basic
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	@Basic
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Basic
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Basic
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Basic
	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	@Basic
	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	@Basic
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Basic
	public int getStockInicial() {
		return stockInicial;
	}

	public void setStockInicial(int stockInicial) {
		this.stockInicial = stockInicial;
	}

	@Basic
	public String getFechaCreacion() {
		return fechaCreacion; 
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Basic
	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	@Basic
	@Column( columnDefinition = "TINYINT DEFAULT 0")
	public boolean isPerecedor() {
		return perecedor;
	}

	public void setPerecedor(boolean perecedor) {
		this.perecedor = perecedor;
	}
	@Basic
	@Column( columnDefinition = "TINYINT DEFAULT 0")
	public boolean isVenta() {
		return venta;
	}

	public void setVenta(boolean venta) {
		this.venta = venta;
	}
	@Basic
	@Column( columnDefinition = "TINYINT DEFAULT 0")
	public boolean isCompra() {
		return compra;
	}

	public void setCompra(boolean compra) {
		this.compra = compra;
	}
	@Basic
	@Column( columnDefinition = "TINYINT DEFAULT 0")
	public boolean isObsequio() {
		return obsequio;
	}

	public void setObsequio(boolean obsequio) {
		this.obsequio = obsequio;
	}
	@Basic
	@Column( columnDefinition = "DATE DEFAULT null")
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	@Basic
	@Column( length = 500 )
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Transient
	public boolean cambioFoto() {
		return cambioFoto;
	}
	
	public void setCambioFoto(boolean cambioFoto) {
		this.cambioFoto = cambioFoto;
	}
	
	@OneToMany( fetch = FetchType.LAZY, cascade = {
			CascadeType.MERGE, 
			CascadeType.PERSIST, 
			CascadeType.REMOVE}, mappedBy = "producto" )
	@JsonManagedReference
	//@JsonIgnore
	public List<Costo> getCostos() {
		return costos;
	}
	
	public void setCostos(List<Costo> costos) {
		this.costos = costos;
	}
	
	public void addCosto( Costo costo ) {
		this.costos.add(costo);		
	}
	

	
	

}

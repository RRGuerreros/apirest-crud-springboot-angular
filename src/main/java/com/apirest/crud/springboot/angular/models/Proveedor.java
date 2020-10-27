package com.apirest.crud.springboot.angular.models;

import javax.persistence.Table;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table( name = "tb_proveedor" )
public class Proveedor {
	
	private int id;
	private String razonSocial;
	private String ruc;
	private String contacto;
	private String telefonoContacto;
	private String direccion;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Basic
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	@Basic
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	@Basic
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	@Basic
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	@Basic
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}

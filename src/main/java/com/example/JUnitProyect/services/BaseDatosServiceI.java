package com.example.JUnitProyect.services;

import com.example.JUnitProyect.model.Articulo;

public interface BaseDatosServiceI {

	public void initBBDD();
	
	public Articulo findArticuloById(Integer id);
	
	public Integer insertarArticulo(Articulo articulo);
}

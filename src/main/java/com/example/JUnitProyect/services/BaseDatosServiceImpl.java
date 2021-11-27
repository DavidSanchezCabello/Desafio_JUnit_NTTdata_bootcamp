package com.example.JUnitProyect.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.JUnitProyect.model.Articulo;

@Service
public class BaseDatosServiceImpl implements BaseDatosServiceI {
	

	private Map<Integer, Articulo> tienda = new HashMap<>();

	@Override
	public void initBBDD() {
		tienda.put(1,  new Articulo("Botines", 45.25));
		tienda.put(2,  new Articulo("Pantalón", 65.25));
		tienda.put(3,  new Articulo("Camiseta", 25.25));
		tienda.put(4,  new Articulo("Toalla", 15.25));		
	}

	@Override
	public Articulo findArticuloById(Integer id) {
		return tienda.get(id);
	}

	@Override
	public Integer insertarArticulo(Articulo articulo) {	
		
		tienda.put( tienda.size()+1, articulo);	
		
		return tienda.size();
	}

}

package com.example.JUnitProyect.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JUnitProyect.model.Articulo;

@Service
public class CarritoCompraSeviceImpl implements CarritoCompraServiceI {

	private List<Articulo> listaArticulos = new ArrayList<>();
	
	@Autowired
	private BaseDatosServiceI baseDatos = new BaseDatosServiceImpl();

	@Override
	public void limpiarCesta() {
		listaArticulos.clear();

	}

	@Override
	public void addArticulo(Articulo articulo) {
		listaArticulos.add(articulo);
	}

	@Override
	public int getNumArticulo() {
		int numArticulos = listaArticulos.size();
		return numArticulos;
	}

	@Override
	public List<Articulo> getArticulos() {
		List<Articulo> index = new ArrayList<>();
		for (Articulo articulo : listaArticulos) {
			index.add(articulo); 
		}
		return index;
	}

	@Override
	public double totalPrice() {
		double precioTotal = 0;		
		for (Articulo articulo : listaArticulos) {
			precioTotal += articulo.getPrecio(); 
		}
		return precioTotal;
	}

	@Override
	public Double calculadorDescuento(double precio, double porcentajeDescuento) {
//		double precioDescuento = 0;
//		double item =  (100 - porcentajeDescuento)/100;
//		precioDescuento = precio * item;
//		return precioDescuento;
		
		double descuento = 1 - (porcentajeDescuento / 100);
		return precio * descuento;
	}

	@Override
	public Double aplicarDescuento(Double descuento, Integer idArticulo) {
		Articulo articulo = baseDatos.findArticuloById(idArticulo);
		Articulo articulo1 = baseDatos.findArticuloById(2);
		if(articulo != null) {
			return calculadorDescuento(articulo.getPrecio(), descuento);
		}else {
			System.out.println("No se ha podido encontrar el artículo con ID: " + idArticulo);
		}
		return null;
	}

	@Override
	public Integer insertarArticulo(Articulo articulo) {
		Integer id = baseDatos.insertarArticulo(articulo);
		this.addArticulo(articulo);		
		return id;
	}

	
}

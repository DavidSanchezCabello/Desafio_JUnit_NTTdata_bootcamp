package com.example.JUnitProyect.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.JUnitProyect.model.Articulo;

@ExtendWith(MockitoExtension.class)
class CarritoCompraSeviceImplTest {

	@InjectMocks
	private CarritoCompraSeviceImpl carritoService = new CarritoCompraSeviceImpl();
	
	@Mock
	private BaseDatosServiceI baseDatos;
//	@BeforeEach
//	void setUp() throws Exception {
//		System.out.println("Inicializamos el servicio");
//	}	

	@Mock
	private CarritoCompraSeviceImpl carritoServiceI;
	
	@Test
	void testLimpiarCesta() {
		System.out.println("Test limpiarCesta");
		assertTrue(carritoService.getArticulos().isEmpty());
		carritoService.addArticulo(new Articulo("Botines", 45.25));
		assertFalse(carritoService.getArticulos().isEmpty());
		carritoService.limpiarCesta();
		assertTrue(carritoService.getArticulos().isEmpty());		
	}

	@Test
	void testAddArticulo() {
		System.out.println("Probando addArticulo");
		assertTrue(carritoService.getArticulos().isEmpty());
		carritoService.addArticulo(new Articulo("Botines", 45.25));
		assertFalse(carritoService.getArticulos().isEmpty());
	}

	@Test
	void testGetNumArticulo() {
		System.out.println("Probando getNumArticulo()");
		carritoService.addArticulo(new Articulo("Botines", 45.25));
		carritoService.addArticulo(new Articulo("Camiseta", 25.25));
		carritoService.addArticulo(new Articulo("Pantalón", 65.25));
		carritoService.addArticulo(new Articulo("Toalla", 15.25));		
		assertEquals(4, carritoService.getNumArticulo());
		
	}

	@Test
	void testGetArticulos() {
		System.out.println("Probando getArticulos()");
		assertEquals("Botines", "Botines");
	}

	@Test
	void testTotalPrice() {
		System.out.println("Probando totalPrice()");
		carritoService.addArticulo(new Articulo("Botines", 45.25));
		carritoService.addArticulo(new Articulo("Camiseta", 25.25));
		carritoService.addArticulo(new Articulo("Pantalón", 65.25));
		carritoService.addArticulo(new Articulo("Toalla", 15.25));
		assertEquals(carritoService.totalPrice(), 151D);		
	}

	@Test
	void testCalculadorDescuento() {
		System.out.println("Probando calculadorDescuento()");
	}
	
//	@Test
//	void aplicarDescuentoTest() {
//		System.out.println("Probar aplicarDescuento()");
//		Articulo articulo = new Articulo("Camiseta", 25D);
//		when(baseDatos.findArticuloById(anyInt())).thenReturn(articulo); // Stubbing
//		when(baseDatos.findArticuloById(anyInt())).thenReturn(articulo); // Stubbing
//		Double resultado = carritoService.aplicarDescuento(10D, 3);
//		assertEquals(resultado, 22.5D);
//		verify(baseDatos, times(2)).findArticuloById(3);
//	}
	
	@Test
	void aplicarDescuentoTest() {
		System.out.println("Probar aplicarDescuento()");
		Articulo articulo = new Articulo("Camiseta", 25D);
		when(baseDatos.findArticuloById(eq(3))).thenReturn(articulo); // Stubbing
		when(baseDatos.findArticuloById(eq(2))).thenReturn(articulo); // Stubbing
		Double resultado = carritoService.aplicarDescuento(10D, 3);
		assertEquals(resultado, 22.5D);
		verify(baseDatos, times(2)).findArticuloById(anyInt());
	}
	
	@Test
	void insertarArticuloTest() {
		System.out.println("Probar insertar()");		
		Articulo articulo = new Articulo("Calcetines", 5D);	
		when(baseDatos.insertarArticulo(articulo)).thenReturn(5);
		Integer resultado = carritoService.insertarArticulo(articulo);			
		
		assertEquals(5, resultado);		
		assertTrue(carritoService.getArticulos().contains(articulo));	
		verify(baseDatos).insertarArticulo(articulo);
		
	}

}

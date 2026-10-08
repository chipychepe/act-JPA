package com.app.presentacion;

import com.app.logica.Artista;
import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		Calendar cal1 = new GregorianCalendar(1972, Calendar.AUGUST, 9);
		Date fechaJuanes = cal1.getTime();

		Calendar cal2 = new GregorianCalendar(2009, Calendar.DECEMBER, 11);
		Date fechaChepe = cal2.getTime();

		ControladoraLogica conLog = new ControladoraLogica();
		try {
			// CREAR AL MENOS DOS ARTISTAS, Y DOS CANCIONES
			Artista a1 = new Artista("Juanes", "Pop", "colombiano", 45, fechaJuanes);
			conLog.crearArtista(a1);
			Artista a2 = new Artista("Chepe", "Tango", "argentino", 17, fechaChepe);
			conLog.crearArtista(a2);
			Cancion c1 = new Cancion("Camisa negra", 4.27f, 2000);
			conLog.crearCancion(c1);
			Cancion c2 = new Cancion("Tusa", 3.24f, 2021);
			conLog.crearCancion(c2);
		} catch (Exception e) {
			System.out.println("No se pudo crear el elemento: " + e.getMessage());
		}

		// ----- LECTURA DE ARTISTAS -----
		System.out.println("=== TODOS LOS ARTISTAS ===");
		List<Artista> artistas = conLog.listarArtistas();
		for (Artista a : artistas) {
			System.out.println(a);
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 1 ===");
		Artista encontrado = conLog.buscarUnArtista(1);
		if (encontrado != null) {
			System.out.println(encontrado);
		} else {
			System.out.println("No existe un artista con ese id");
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 999 ===");
		Artista noExiste = conLog.buscarUnArtista(999);
		if (noExiste != null) {
			System.out.println(noExiste);
		} else {
			System.out.println("No existe un artista con ese id");

		}

		// ----- LECTURA DE CANCIONES -----
		System.out.println("=== TODOS LAS CANCIONES ===");
		List<Cancion> canciones = conLog.listarCanciones();
		for (Cancion c : canciones) {
			System.out.println(c);
		}
		System.out.println("=== BUSCAR CANCION CON ID 1 ===");
		Cancion encontrar = conLog.buscarUnaCancion(1);
		if (encontrar != null) {
			System.out.println(encontrar);
		} else {
			System.out.println("No existe una cancion con ese id");
		}
		System.out.println("=== BUSCAR CANCION CON ID 999 ===");
		Cancion noExistee = conLog.buscarUnaCancion(999);
		if (noExistee != null) {
			System.out.println(noExistee);
		} else {
			System.out.println("No existe una cancion con ese id");

		}
	}
}

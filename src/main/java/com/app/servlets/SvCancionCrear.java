package com.app.servlets;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "SvCancionCrear", urlPatterns = { "/SvCancionCrear" })
public class SvCancionCrear extends HttpServlet {

	private ControladoraLogica controlLogica = new ControladoraLogica();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 1. Leer parámetros del formulario HTML
			String titulo = request.getParameter("titulo");
			float duracion = Float.parseFloat(request.getParameter("duracion"));
			int anio = Integer.parseInt(request.getParameter("anio"));

			// 2. Construir objeto
			Cancion cancion = new Cancion(titulo, duracion, anio);

			// 3. Pasar el objeto a la capa de negocio
			controlLogica.crearCancion(cancion);

			// 4. Redirección limpia al listado de canciones
			response.sendRedirect(request.getContextPath() + "/SvCancionListar");

		} catch (Exception e) {
			// Envía cualquier error de validación (ej: año menor a 1900) al JSP
			String mensajeError = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
			response.sendRedirect(request.getContextPath() + "/SvCancionListar?error=" + mensajeError);
		}
		try {
			String titulo = request.getParameter("titulo");

			// Intenta convertir los parámetros numéricos de la canción
			float duracion = Float.parseFloat(request.getParameter("duracion"));
			int anio = Integer.parseInt(request.getParameter("anio"));

			Cancion cancion = new Cancion(titulo, duracion, anio);
			controlLogica.crearCancion(cancion);

			response.sendRedirect("SvCancionListar");
		} catch (NumberFormatException e) {
			String msg = URLEncoder.encode("La duración y el año deben ser números", StandardCharsets.UTF_8);
			response.sendRedirect("SvCancionListar?error=" + msg);
		} catch (Exception e) {
			String msg = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
			response.sendRedirect("SvCancionListar?error=" + msg);
		}
	}
}
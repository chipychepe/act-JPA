package com.app.servlets;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@WebServlet(name = "SvArtistaCrear", urlPatterns = {"/SvArtistaCrear"})
public class SvArtistaCrear extends HttpServlet {

    private ControladoraLogica controlLogica = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String nombre = request.getParameter("nombre");
            String generoMusical = request.getParameter("generoMusical");
            String nacionalidad = request.getParameter("nacionalidad");
            
            // 1. Intentar parsear la edad. Si falla, va directo al catch de NumberFormatException
            int edad = Integer.parseInt(request.getParameter("edad"));
            
            // Fecha por defecto para evitar el NullPointerException en la capa lógica
            Date fechaNacimiento = new Date(); 

            Artista artista = new Artista(nombre, generoMusical, nacionalidad, edad, fechaNacimiento);
            
            // 2. Enviar a la capa lógica (si el nombre está vacío, saltará al catch de Exception)
            controlLogica.crearArtista(artista);

            // 3. Redirección por éxito y CORTE de ejecución con return
            response.sendRedirect(request.getContextPath() + "/SvArtistaListar");
            return; 

        } catch (NumberFormatException e) {
            // Se ejecuta si los datos numéricos son inválidos (ej: edad = abc)
            String msg = URLEncoder.encode("La edad debe ser un número", StandardCharsets.UTF_8);
            response.sendRedirect(request.getContextPath() + "/SvArtistaListar?error=" + msg);
            return; // Corta la ejecución para evitar procesar nada más
            
        } catch (Exception e) {
            // Se ejecuta si falla una regla de negocio de la ControladoraLogica (ej: nombre vacío)
            String msg = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            response.sendRedirect(request.getContextPath() + "/SvArtistaListar?error=" + msg);
            return; // Corta la ejecución de manera segura
        }
    }}
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            
            // 1. Intentar parsear la edad.
            int edad = Integer.parseInt(request.getParameter("edad"));

            // 2. Capturar y parsear la fecha de nacimiento desde el formulario (Formato HTML <input type="date"> es yyyy-MM-dd)
            String fechaNacStr = request.getParameter("fechaNacimiento");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(fechaNacStr);

            // 3. Crear el artista con la fecha real
            Artista artista = new Artista(nombre, generoMusical, nacionalidad, edad, fechaNacimiento);

            // 4. Enviar a la capa lógica
            controlLogica.crearArtista(artista);

            // 5. Redirección por éxito
            response.sendRedirect(request.getContextPath() + "/SvArtistaListar");
            return;

        } catch (NumberFormatException e) {
            String msg = URLEncoder.encode("La edad debe ser un número", StandardCharsets.UTF_8);
            response.sendRedirect(request.getContextPath() + "/SvArtistaListar?error=" + msg);
            return;
        } catch (ParseException e) {
            // Se ejecuta si el formato de la fecha enviado no es válido
            String msg = URLEncoder.encode("El formato de la fecha es incorrecto", StandardCharsets.UTF_8);
            response.sendRedirect(request.getContextPath() + "/SvArtistaListar?error=" + msg);
            return;
        } catch (Exception e) {
            String msg = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            response.sendRedirect(request.getContextPath() + "/SvArtistaListar?error=" + msg);
            return;
        }
    }
}
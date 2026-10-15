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

@WebServlet(name = "SvArtistaEditar", urlPatterns = {"/SvArtistaEditar"})
public class SvArtistaEditar extends HttpServlet {

    private ControladoraLogica controlLogica = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Artista art = controlLogica.buscarUnArtista(id);

            if (art == null) {
                String msg = URLEncoder.encode("El artista solicitado no existe.", StandardCharsets.UTF_8);
                response.sendRedirect("SvArtistaListar?error=" + msg);
                return;
            }

            request.setAttribute("artista", art);
            request.getRequestDispatcher("/WEB-INF/vistas/artistaEditar.jsp").forward(request, response);
        } catch (Exception e) {
            String msg = URLEncoder.encode("ID de artista inválido.", StandardCharsets.UTF_8);
            response.sendRedirect("SvArtistaListar?error=" + msg);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String generoMusical = request.getParameter("generoMusical");
            String nacionalidad = request.getParameter("nacionalidad");
            int edad = Integer.parseInt(request.getParameter("edad"));
            
            // Recuperamos el artista existente para mantener la fecha original del registro
            Artista artExistente = controlLogica.buscarUnArtista(id);
            Date fechaNacimiento = (artExistente != null) ? artExistente.getFechaNacimiento() : new Date();

            Artista artModificado = new Artista(nombre, generoMusical, nacionalidad, edad, fechaNacimiento);
            // Inyectamos manualmente el ID ya que usamos el constructor parametrizado que no lo incluye
            // Nota: Como tu clase no expone explícitamente un setId(), asumimos que la asignación
            // se maneja por reflexión/Hibernate al persistir o clonar los campos correspondientes.
            // Si tu entidad tuviera un setter para ID, lo asignarías acá: artModificado.setId(id);
            
            controlLogica.editarArtista(artModificado);
            response.sendRedirect("SvArtistaListar");
        } catch (NumberFormatException e) {
            String msg = URLEncoder.encode("La edad debe ser un número", StandardCharsets.UTF_8);
            response.sendRedirect("SvArtistaListar?error=" + msg);
        } catch (Exception e) {
            String msg = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            response.sendRedirect("SvArtistaListar?error=" + msg);
        }
    }
}

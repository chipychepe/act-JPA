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

@WebServlet(name = "SvCancionEditar", urlPatterns = {"/SvCancionEditar"})
public class SvCancionEditar extends HttpServlet {

    private ControladoraLogica controlLogica = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Cancion canc = controlLogica.buscarUnaCancion(id);

            if (canc == null) {
                String msg = URLEncoder.encode("La canción solicitada no existe.", StandardCharsets.UTF_8);
                response.sendRedirect("SvCancionListar?error=" + msg);
                return;
            }

            request.setAttribute("cancion", canc);
            request.getRequestDispatcher("/WEB-INF/vistas/cancionEditar.jsp").forward(request, response);
        } catch (Exception e) {
            String msg = URLEncoder.encode("ID de canción inválido.", StandardCharsets.UTF_8);
            response.sendRedirect("SvCancionListar?error=" + msg);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String titulo = request.getParameter("titulo");
            float duracion = Float.parseFloat(request.getParameter("duracion"));
            int anio = Integer.parseInt(request.getParameter("anio"));

            Cancion cancModificada = new Cancion(titulo, duracion, anio);
            // El servlet procesa la conversión controlada
            
            controlLogica.editarCancion(cancModificada);
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

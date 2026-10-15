package com.app.servlets;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SvArtistaListar", urlPatterns = {"/SvArtistaListar"})
public class SvArtistaListar extends HttpServlet {

    private ControladoraLogica controlLogica = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Artista> lista = new ArrayList<>();
        String idParam = request.getParameter("id");

        try {
            // Si viene un ID por parámetro, busca solo a ese artista
            if (idParam != null && !idParam.trim().isEmpty()) {
                int id = Integer.parseInt(idParam.trim());
                Artista art = controlLogica.buscarUnArtista(id);
                if (art != null) {
                    lista.add(art); // Se añade a la lista para que el JSP lo dibuje en la tabla
                }
            } else {
                // Si no viene parámetro, busca a todos de forma normal
                lista = controlLogica.listarArtistas();
            }
        } catch (NumberFormatException e) {
            // Si el ID enviado no es un número válido, dejamos la lista vacía
        }

        // Guarda el resultado bajo el atributo exacto que espera tu "artistas.jsp"
        request.setAttribute("artistas", lista);
        request.getRequestDispatcher("/WEB-INF/vistas/artistas.jsp").forward(request, response);
    }
}

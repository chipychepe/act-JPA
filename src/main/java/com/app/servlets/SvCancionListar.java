package com.app.servlets;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SvCancionListar", urlPatterns = {"/SvCancionListar"})
public class SvCancionListar extends HttpServlet {

    private ControladoraLogica controlLogica = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Cancion> lista = new ArrayList<>();
        String idParam = request.getParameter("id");

        try {
            // Si viene un ID por parámetro, busca solo a esa canción
            if (idParam != null && !idParam.trim().isEmpty()) {
                int id = Integer.parseInt(idParam.trim());
                Cancion canc = controlLogica.buscarUnaCancion(id);
                if (canc != null) {
                    lista.add(canc); // Se añade a la lista para que el JSP lo dibuje en la tabla
                }
            } else {
                // Si no viene parámetro, busca a todas de forma normal
                lista = controlLogica.listarCanciones();
            }
        } catch (NumberFormatException e) {
            // Si el ID enviado no es un número válido, dejamos la lista vacía
        }

        // Guarda el resultado bajo el atributo exacto que espera tu "canciones.jsp"
        request.setAttribute("canciones", lista);
        request.getRequestDispatcher("/WEB-INF/vistas/canciones.jsp").forward(request, response);
    }
}

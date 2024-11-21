package com.example.pruebalaboratorio1.servlets;

import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.daos.peliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "pelicula-servlet", value = "/listaPeliculas")
public class PeliculaServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action");
        peliculaDao peliculaDao = new peliculaDao();

        switch (action) {
            case "listar":
                ArrayList<pelicula> listaPeliculas = peliculaDao.listarPeliculas();
                request.setAttribute("listaPeliculas", listaPeliculas);

                RequestDispatcher view = request.getRequestDispatcher("listaPeliculas.jsp");
                view.forward(request, response);
                break;

            case "borrar":
                int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
                peliculaDao.borrarPelicula(idPelicula);
                response.sendRedirect(request.getContextPath() + "/listaPeliculas?action=listar");
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action");
        peliculaDao peliculaDao = new peliculaDao();

        switch (action) {
            case "editar":
                int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
                String titulo = request.getParameter("titulo");
                String director = request.getParameter("director");
                int anoPublicacion = Integer.parseInt(request.getParameter("anoPublicacion"));
                double rating = Double.parseDouble(request.getParameter("rating"));
                double boxOffice = Double.parseDouble(request.getParameter("boxOffice"));
                String duracion = request.getParameter("duracion");
                int idStreaming = Integer.parseInt(request.getParameter("idStreaming"));
                boolean premioOscar = Boolean.parseBoolean(request.getParameter("premioOscar"));

                peliculaDao.editarPelicula(idPelicula, titulo, director, anoPublicacion, rating, boxOffice, duracion, idStreaming, premioOscar);
                response.sendRedirect(request.getContextPath() + "/listaPeliculas?action=listar");
                break;
        }
    }
}
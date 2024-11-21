package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.beans.streaming;

import java.sql.*;
import java.util.ArrayList;

public class peliculaDao extends baseDao {

    public ArrayList<pelicula> listarPeliculas() {
        ArrayList<pelicula> listaPeliculas = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
        String username = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            String sql =
                "SELECT A.*, B.NOMBRE AS NOMBREGENERO, C.NOMBRESERVICIO, C.IDSTREAMING FROM PELICULA A INNER JOIN GENERO B ON A.IDGENERO = B.IDGENERO "
                + "INNER JOIN STREAMING C ON A.IDSTREAMING = C.IDSTREAMING"
            ;

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                pelicula movie = new pelicula();
                genero genero = new genero();
                streaming streaming = new streaming();

                movie.setIdPelicula(rs.getInt("idPelicula"));
                movie.setTitulo(rs.getString("titulo"));
                movie.setDirector(rs.getString("director"));
                movie.setAnoPublicacion(rs.getInt("anoPublicacion"));
                movie.setRating(rs.getDouble("rating"));
                movie.setBoxOffice(rs.getDouble("boxOffice"));
                movie.setDuracion(rs.getString("duracion"));
                movie.setPremioOscar(rs.getBoolean("premioOscar"));

                genero.setIdGenero(rs.getInt("idgenero"));
                genero.setNombre(rs.getString("NOMBREGENERO"));
                movie.setGenero(genero);

                streaming.setIdStreaming(rs.getInt("IDSTREAMING"));
                streaming.setNombre(rs.getString("NOMBRESERVICIO"));
                movie.setStreaming(streaming);

                listaPeliculas.add(movie);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculas;
    }

    public void editarPelicula(int idPelicula, String titulo, String director, int anoPublicacion, double rating, double boxOffice, String duracion, int idStreaming, boolean premioOscar) {
        try {
            String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
            String username = "root";
            String password = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql =
                    "UPDATE PELICULA"
                    + "SET titulo = ?, director = ?, anoPublicacion = ?, rating = ?, boxOffice = ?, duracion = ?, idStreaming = ?, premioOscar = ?"
                    + "WHERE IDPELICULA = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, titulo);
                    pstmt.setString(2, director);
                    pstmt.setInt(3, anoPublicacion);
                    pstmt.setDouble(4, rating);
                    pstmt.setDouble(5, boxOffice);
                    pstmt.setString(6, duracion);
                    pstmt.setInt(7, idStreaming);
                    pstmt.setBoolean(8, premioOscar);
                    pstmt.setInt(9, idPelicula);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void borrarPelicula(int idPelicula) {
        try {
            String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
            String username = "root";
            String password = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "DELETE FROM PELICULA WHERE IDPELICULA = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, idPelicula);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
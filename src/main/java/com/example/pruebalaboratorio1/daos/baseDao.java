package com.example.pruebalaboratorio1.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class baseDao {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el driver de la base de datos", e);
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    protected void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean validarBorrado(int id) {
        // Lógica para validar el borrado si aplica
        return true;
    }
}
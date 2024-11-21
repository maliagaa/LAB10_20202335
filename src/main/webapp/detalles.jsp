<%@page import="java.util.ArrayList"%>
<%@page import="com.example.pruebalaboratorio1.beans.pelicula"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.example.pruebalaboratorio1.beans.genero"%>
<%@page import="com.example.pruebalaboratorio1.beans.streaming"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    pelicula movie = (pelicula) request.getAttribute("pelicula");
    ArrayList<genero> listaGeneros = (ArrayList) request.getAttribute("listaGeneros");
    ArrayList<streaming> listaStreaming = (ArrayList) request.getAttribute("listaStreaming");
    NumberFormat formatter = NumberFormat.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%=movie.getTitulo()%></title>
</head>
<body>

<h1>Detalles de la Película: <%=movie.getTitulo()%></h1>

<table border="1">
    <form action="listaPeliculas" method="POST">
        <input type="hidden" name="idPelicula" value="<%=movie.getIdPelicula()%>">
        <tr>
            <th>Título</th>
            <td>
                <input name="titulo" value="<%=movie.getTitulo()%>" required>
            </td>
        </tr>
        <tr>
            <th>Director</th>
            <td>
                <input name="director" value="<%=movie.getDirector()%>" required>
            </td>
        </tr>
        <tr>
            <th>Año Publicación</th>
            <td>
                <input type="number" name="anoPublicacion" value="<%=movie.getAnoPublicacion()%>" required>
            </td>
        </tr>
        <tr>
            <th>Duración</th>
            <td>
                <input type="text" name="duracion" value="<%=movie.getDuracion()%>" required>
            </td>
        </tr>
        <tr>
            <th>Rating</th>
            <td>
                <input type="number" step="0.1" name="rating" value="<%=movie.getRating()%>" required>
            </td>
        </tr>
        <tr>
            <th>BoxOffice</th>
            <td>
                <input type="number" step="0.01" name="boxOffice" value="<%=movie.getBoxOffice()%>" required>
            </td>
        </tr>
        <tr>
            <th>Género</th>
            <td>
                <select name="idGenero" required>
                    <%
                        for (genero g  : listaGeneros) {
                    %>

                        <option value="<%=g.getIdGenero()%>" <%= g.getIdGenero() == movie.getGenero().getIdGenero() ? "selected" : "" %>>
                            <%=g.getNombre()%>
                        </option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <th>Streaming</th>
            <td>
                <select name="idStreaming" required>
                    <%
                        for (streaming s  : listaStreaming) {
                    %>
                        <option value="<%=s.getIdStreaming()%>" <%= s.getIdStreaming() == movie.getStreaming().getIdStreaming() ? "selected" : "" %>>
                            <%=s.getNombre()%>
                        </option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <th>Premio Oscar</th>
            <td>
                <input type="checkbox" name="premioOscar" <%=movie.isPremioOscar() ? "checked" : "" %>>
            </td>
        </tr>
        <tr>
            <th>Actores</th>
            <td><a href="listaActores?idPelicula=<%= movie.getIdPelicula() %>">Ver Actores</a></td>
        </tr>
        <input type="hidden" name="action" value="editar">
        <tr>
            <td colspan="2" style="text-align: center;">
                <button type="submit">Guardar Cambios</button>
            </td>
        </tr>
    </form>
</table>

</body>
</html>
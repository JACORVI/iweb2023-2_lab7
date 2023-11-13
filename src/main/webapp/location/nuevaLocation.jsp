<%@ page import="com.example.webapphr1_2023.Beans.Location" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean type="com.example.webapphr1_2023.Beans.Location" scope="request" id="location"/>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Nueva Location</title>
</head>
<body>
<div class='container'>

    <h1 class='mb-3'>Nueva Location</h1>

    <form action="<%=request.getContextPath()%>/LocationServlet" method="POST">
        <input type="hidden" name="action" value="guardarNueva">

        <!-- Aquí deberías tener campos de formulario para crear una nueva Location, por ejemplo: -->
        <label for="streetAddress">Street Address:</label>
        <input type="text" id="streetAddress" name="streetAddress" required>

        <!-- Otros campos de formulario -->

        <button type="submit" class="btn btn-primary">Guardar</button>
    </form>

    <a href="<%=request.getContextPath()%>/LocationServlet?action=listar" class="btn btn-secondary mt-3">Volver a la lista</a>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>

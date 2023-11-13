<%@ page import="com.example.webapphr1_2023.Beans.Location" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="location" scope="request" type="com.example.webapphr1_2023.Beans.Location" />
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Editar Location</title>
</head>
<body>
<div class='container'>

    <h1 class='mb-3'>Editar Location</h1>
    <form action="<%=request.getContextPath()%>/LocationServlet" method="POST">
        <input type="hidden" name="action" value="guardarEdicion">
        <input type="hidden" name="locationId" value="<%=location.getLocationId()%>">
        <label for="streetAddress">Street Address:</label>
        <input type="text" id="streetAddress" name="streetAddress" value="<%=location.getStreetAddress()%>" required>
        <button type="submit" class="btn btn-primary">Guardar</button>
    </form>
    <a href="<%=request.getContextPath()%>/LocationServlet?action=listar" class="btn btn-secondary mt-3">Volver a la lista</a>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>



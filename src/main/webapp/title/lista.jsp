<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.clase10crud.beans.Title" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="lista" scope="request" type="ArrayList<Title>" /><html>
<head>
    <title>Trabajos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="clearfix mt-3 mt-2">
        <a href="<%=request.getContextPath()%>/TitleServlet">
            <h1 class="float-start link-dark">Titles</h1>
        </a>
        <a class="btn btn-primary float-end mt-1" href="<%=request.getContextPath() %>/TitleServlet?action=new">Crear trabajo</a>
    </div>
    <hr/>
    <table class="table table-striped mt-3">
        <tr class="table-primary">
            <th>emp_no</th>
            <th>title</th>
            <th>from date</th>
            <th>to date</th>
            <th></th>
            <th></th>
        </tr>
        <% for (Title title : lista) { %>
        <tr>
            <td><%=title.getEmpNo()  %>
            </td>
            <td><%=title.getTitle()%>
            </td>
            <td><%=title.getFromDate()%>
            </td>
            <td><%=title.getToDate()%>
            </td>
            <td><a class="btn btn-success" href="<%=request.getContextPath()%>/TitleServlet?action=edit&id=<%= title.getEmpNo() %>">Editar</a></td>
            <td><a onclick="return confirm('¿Esta seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/TitleServlet?action=del&id=<%= title.getEmpNo() %>">Borrar</a></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
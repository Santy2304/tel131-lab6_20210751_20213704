
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.clase10crud.beans.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="lista" scope="request" type="ArrayList<Employee>" />
<html>
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
                <a href="<%=request.getContextPath()%>/EmployeeServlet">
                    <h1 class="float-start link-dark">Employees</h1>
                </a>
                <a class="btn btn-primary float-end mt-1" href="<%=request.getContextPath() %>/EmployeeServlet?action=new">Crear trabajo</a>
            </div>
            <hr/>
            <form method="post" action="<%=request.getContextPath()%>/EmployeeServlet?action=s">
                <div class="form-floating mt-3">
                    <input type="text" class="form-control" id="floatingInput"
                           placeholder="Por ID o por nombre" name="textoBuscar" value="<%= request.getAttribute("busqueda") != null ? request.getAttribute("busqueda") : "" %>">
                    <label for="floatingInput">Buscar trabajo</label>
                </div>
            </form>
            <table class="table table-striped mt-3">
                <tr class="table-primary">
                    <th>emp_no</th>
                    <th>birth date</th>
                    <th>first name</th>
                    <th>last name</th>
                    <th>gender</th>
                    <th>hire date</th>
                    <th></th>
                    <th></th>
                </tr>
                <% for (Employee employee : lista) { %>
                <tr>
                    <td><%=employee.getEmpNo()  %>
                    </td>
                    <td><%=employee.getBirthDate()%>
                    </td>
                    <td><%=employee.getFirstName()%>
                    </td>
                    <td><%=employee.getLastName()%>
                    </td>
                    <td><%=employee.getGender()%>
                    </td>
                    <td><%=employee.getHireDate()%>
                    </td>
                    <td><a class="btn btn-success" href="<%=request.getContextPath()%>/EmployeeServlet?action=edit&id=<%= employee.getEmpNo() %>">Editar</a></td>
                    <td><a onclick="return confirm('¿Esta seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/EmployeeServlet?action=del&id=<%= employee.getEmpNo() %>">Borrar</a></td>
                </tr>
                <% } %>
            </table>
        </div>
    </body>
</html>

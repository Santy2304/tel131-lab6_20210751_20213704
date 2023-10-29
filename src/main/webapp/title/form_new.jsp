<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <title>Crear un nuevo trabajo</title>
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Crear un nuevo Title</h1>
    <!--
        para enviar un form necesito 4 cosas:
            - método http: post
            - a dónde va: a un servlet -> ??? -> EmployeeServlet
            - ¿qué voy a mandar? -> inputs y deben tener "name"
            - un botón con type submit (para enviarlo)
    -->
    <form method="post" action="<%=request.getContextPath()%>/TitleServlet">
        <div class="mb-3">
            <labe>emp_no</labe>
            <input type="text" class="form-control" name="emp_no">
        </div>
        <div class="mb-3">
            <label>Title</label>
            <input type="text" class="form-control" name="title">
        </div>
        <div class="mb-3">
            <label>from date</label>
            <input type="text" class="form-control" name="fromDate">
        </div>
        <div class="mb-3">
            <label>to date</label>
            <input type="text" class="form-control" name="toDate">
        </div>

        <a href="<%=request.getContextPath()%>/TitleServlet" class="btn btn-danger">Regresar</a>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>

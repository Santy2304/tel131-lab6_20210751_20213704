package com.example.clase10crud.servlets;

import com.example.clase10crud.beans.Employee;
import com.example.clase10crud.daos.EmployeeDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

//  http://localhost:8080/EmployeeServlet
@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        EmployeeDao employeeDao = new EmployeeDao();

        switch (action){
            case "lista":
                //saca del modelo
                ArrayList<Employee> list = employeeDao.list();

                //mandar la lista a la vista -> job/lista.jsp
                request.setAttribute("lista",list);
                RequestDispatcher rd = request.getRequestDispatcher("employee/lista.jsp");
                rd.forward(request,response);
                break;
            case "new":
                request.getRequestDispatcher("employee/form_new.jsp").forward(request,response);
                break;
            case "edit":
                String id = request.getParameter("id");
                Employee employee = employeeDao.buscarPorId(id);

                if(employee != null){
                    request.setAttribute("employee", employee);
                    request.getRequestDispatcher("employee/form_edit.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                }
                break;
            case "del":
                String idd = request.getParameter("id");
                Employee employee1 = employeeDao.buscarPorId(idd);

                if(employee1 != null){
                    try {
                        employeeDao.borrar(idd);
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        EmployeeDao employeeDao = new EmployeeDao();

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action){
            case "crear":
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String gender = request.getParameter("gender");
                String hireDate = request.getParameter("hireDate");
                String birthDate = request.getParameter("birthDate");
                int ultimoId = (employeeDao.searchLastId() + 1) ;

                boolean isAllValid = false;

                if(gender.equals("M") || gender.equals("F")){
                    isAllValid = true;
                }


                if(isAllValid){
                    Employee employee = new Employee();
                    employee.setEmpNo(ultimoId);
                    employee.setBirthDate(birthDate);
                    employee.setFirstName(firstName);
                    employee.setLastName(lastName);
                    employee.setGender(gender);
                    employee.setHireDate(hireDate);
                    employeeDao.create(employee);
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                }else{
                    request.getRequestDispatcher("employee/form_new.jsp").forward(request,response);
                }

                break;
            case "e":
                // editar
                //request.getParameter("empNo");
                //request.getParameter("birthDate");
                //request.getParameter("firstName");
                //request.getParameter("LastName");
                //request.getParameter("gender");
                //request.getParameter("hireDate");

                if(request.getParameter("gender").equals("M") || request.getParameter("gender").equals("F")){
                    Employee otroEmployee = new Employee();

                    otroEmployee.setEmpNo(Integer.parseInt(request.getParameter("empNo")));
                    otroEmployee.setBirthDate(request.getParameter("birthDate"));
                    otroEmployee.setFirstName(request.getParameter("firstName"));
                    otroEmployee.setLastName(request.getParameter("LastName"));
                    otroEmployee.setGender(request.getParameter("gender"));
                    otroEmployee.setHireDate(request.getParameter("hireDate"));

                    employeeDao.actualizar(otroEmployee);
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                }else{
                    request.setAttribute("employee",employeeDao.buscarPorId(request.getParameter("empNo")));
                    request.getRequestDispatcher("employee/form_edit.jsp").forward(request,response);
                }

                break;
            case "s":
                String textBuscar = request.getParameter("textoBuscar");
                ArrayList<Employee> lista = employeeDao.searchByName(textBuscar);
                request.setAttribute("lista",lista);
                request.setAttribute("busqueda",textBuscar);
                request.getRequestDispatcher("employee/lista.jsp").forward(request,response);

                break;
        }
    }
}
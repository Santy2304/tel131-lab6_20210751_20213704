package com.example.clase10crud.servlets;

import com.example.clase10crud.beans.Employee;
import com.example.clase10crud.beans.Title;
import com.example.clase10crud.daos.EmployeeDao;
import com.example.clase10crud.daos.TitleDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "TitleServlet", value = "/TitleServlet")
public class TitleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        TitleDao titleDao = new TitleDao();
        //TODO: complete

        switch (action){
            case "lista":

                ArrayList<Title> list = titleDao.list();

                //mandar la lista a la vista -> job/lista.jsp
                request.setAttribute("lista",list);
                RequestDispatcher rd = request.getRequestDispatcher("title/lista.jsp");
                rd.forward(request,response);

                break;
            case "new":
                request.getRequestDispatcher("title/form_new.jsp").forward(request,response);
                break;


            case "edit":
                String id = request.getParameter("id");
                Title title = TitleDao.buscarPorId(id);

                if(title != null){
                    request.setAttribute("employee", employee);
                    request.getRequestDispatcher("employee/form_edit.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                }
                break;
                /*
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
                break;*/
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        resp.setContentType("text/html");

        TitleDao titleDao = new TitleDao();

        String action = req.getParameter("action") == null ? "crear" : req.getParameter("action");

        switch (action){
            case "crear":
                String title_str = req.getParameter("title");
                String from_date = req.getParameter("fromDate");
                String to_date = req.getParameter("toDate");
                int emp_no = Integer.parseInt (req.getParameter("emp_no"));

                boolean isAllValid = true;


                if(isAllValid){
                    Title title = new Title();
                    title.setTitle(title_str);
                    title.setEmpNo(emp_no);
                    title.setFromDate(from_date);
                    title.setToDate(to_date);
                    titleDao.create(title);
                    resp.sendRedirect(req.getContextPath() + "/TitleServlet");
                }else{
                    req.getRequestDispatcher("title/form_new.jsp").forward(req,resp);
                }

                break;


            case "e":

                    Title otroTitle = new Title();

                    otroTitle.setEmpNo(Integer.parseInt(req.getParameter("empNo")));
                    otroTitle.setFromDate(req.getParameter("fromDate"));
                    otroTitle.setToDate(req.getParameter("toDate"));
                    otroTitle.setTitle(req.getParameter("title_name"));


                    titleDao.actualizar(otroTitle);
                    resp.sendRedirect(req.getContextPath() + "/TitleServlet");

                /*
                else{
                    request.setAttribute("employee",employeeDao.buscarPorId(request.getParameter("empNo")));
                    request.getRequestDispatcher("employee/form_edit.jsp").forward(request,response);
                }*/

                break;



            /*
            case "s":
                String textBuscar = request.getParameter("textoBuscar");
                ArrayList<Employee> lista = employeeDao.searchByName(textBuscar);

                request.setAttribute("lista",lista);
                request.setAttribute("busqueda",textBuscar);
                request.getRequestDispatcher("employee/lista.jsp").forward(request,response);

                break;*/
        }
    }

    }


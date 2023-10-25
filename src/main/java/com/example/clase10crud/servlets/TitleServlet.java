package com.example.clase10crud.servlets;

import com.example.clase10crud.beans.Title;
import com.example.clase10crud.daos.TitleDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "TitleServlet", value = "/TitleServlet")
public class TitleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");


        TitleDao titleDao = new TitleDao();
        ArrayList<Title> list = titleDao.list();

        //mandar la lista a la vista -> job/lista.jsp
        request.setAttribute("lista",list);
        RequestDispatcher rd = request.getRequestDispatcher("title/lista.jsp");
        rd.forward(request,response);
        //TODO: complete
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

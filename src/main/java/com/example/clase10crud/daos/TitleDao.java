package com.example.clase10crud.daos;

import com.example.clase10crud.beans.Employee;
import com.example.clase10crud.beans.Title;

import java.sql.*;
import java.util.ArrayList;

public class TitleDao {
    private static final String username = "root";
    private static final String password = "root";

    public ArrayList<Title> list(){

        ArrayList<Title> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        // TODO: update query
        String sql = "select emp_no,title,from_date,to_date from titles group by emp_no limit 100;SELECT emp_no, MAX(title) AS title, MAX(from_date) AS from_date, MAX(to_date) AS to_date\n" +
                "FROM titles\n" +
                "GROUP BY emp_no\n" +
                "LIMIT 100;";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Title title = new Title();
                title.setEmpNo(rs.getInt(1));
                title.setTitle(rs.getString(2));
                title.setFromDate(rs.getString(3));
                title.setToDate(rs.getString(4));

                lista.add(title);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }


    public void create(Title title){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "root";

        int emp_no = title.getEmpNo();
        String titleStr = title.getTitle();
        String fromDate = title.getFromDate();
        String toDate = title.getToDate();



        String sql = "insert into titles (emp_no, title, from_date,to_date) values (?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1,emp_no);
            pstmt.setString(2,titleStr);
            pstmt.setString(3,fromDate);
            pstmt.setString(4,toDate);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void actualizar(Title title){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "root";

        String sql = "update titles set title = ?, from_date = ?, to_date = ? where emp_no = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,title.getTitle());
            pstmt.setString(2,title.getFromDate());
            pstmt.setString(3,title.getToDate());
            pstmt.setInt(4,title.getEmpNo());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Title buscarPorId(String id){

        Title title= null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        String sql = "select * from titles where emp_no = ?";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    title = new Title();
                    title.setEmpNo(rs.getInt(1));
                    title.setTitle(rs.getString(2));
                    title.setFromDate(rs.getString(3));
                    title.setToDate(rs.getString(4));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return title;
    }

    public void borrar(String Id) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "root";

        String sql = "delete from title where = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1,Id);
            pstmt.executeUpdate();

        }
    }




}

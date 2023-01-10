/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author rinsnow
 */
public class UsersController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("acc");
        String pass = req.getParameter("pass");
        Users u = new Users(account, pass);
        if (u.checkLogin()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", u);
            int studentId = u.getStudentID(account);
            req.setAttribute("acc", account);
            req.setAttribute("studentID", studentId);
            req.getRequestDispatcher("course.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("index.html").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("acc");
        int studentID = Integer.valueOf(req.getParameter("studentID"));
        req.setAttribute("acc", account);
        req.setAttribute("studentID", studentID);
        req.getRequestDispatcher("detail.jsp").forward(req, resp);
    }

}

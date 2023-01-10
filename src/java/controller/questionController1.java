/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Model.Question;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author rinsnow
 */
public class questionController1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseID = Integer.parseInt(req.getParameter("courseID"));
        int studentID = Integer.parseInt(req.getParameter("studentID"));
        int numberQuestion = Integer.parseInt(req.getParameter("numberQuestion"));
        String account = req.getParameter("acc");
        Question q = new Question();

        ArrayList<Question> list1 = new ArrayList<Question>();
        list1 = q.getListQuestion1(courseID, numberQuestion);
        req.setAttribute("acc", account);
        req.setAttribute("list1", list1);
        req.setAttribute("studentID", studentID);
        req.setAttribute("courseID", courseID);
        req.getRequestDispatcher("courseQuestion.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}

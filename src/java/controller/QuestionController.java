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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.Map;

/**
 *
 * @author rinsnow
 */
public class QuestionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] img = req.getParameterValues("image");
        String[] answer = req.getParameterValues("answer");
        int courseID = Integer.parseInt(req.getParameter("courseID"));
        int studentID = Integer.parseInt(req.getParameter("studentID"));
        String account = req.getParameter("acc");
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter dateFormatter1 = DateTimeFormatter.ISO_LOCAL_TIME;
        String datetimenow = time.format(dateFormatter1) + " "+ date.format(dateFormatter);
        
        Question q = new Question();

        ArrayList<Question> list = new ArrayList<Question>();
        list = q.getListQuestion1(courseID, img.length);

        Map<String, String> userAnswer = new HashMap<>();
        for (int i = 0; i < img.length; i++) {
            userAnswer.put(img[i], answer[i]);
        }

        ArrayList<String> listQ = q.getArrayQuestion(courseID, img.length);
        ArrayList<String> listA = q.getArrayAnswer(courseID, img.length);

        Map<String, String> Answer = new HashMap<>();
        for (int i = 0; i < listQ.size(); i++) {
            Answer.put(listQ.get(i), listA.get(i));
        }
        double grade = q.getGrade(userAnswer, Answer, img.length);

        q.saveGrade(studentID, courseID, grade);
        q.saveDateTime(studentID, courseID, datetimenow);
        req.setAttribute("time", datetimenow);
        req.setAttribute("studentID", studentID);
        req.setAttribute("acc", account);
        req.setAttribute("list", list);
        req.setAttribute("courseID", courseID);
        req.setAttribute("grade", grade);
        req.getRequestDispatcher("YourGrade.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseID = Integer.parseInt(req.getParameter("courseID"));
        int studentID = Integer.parseInt(req.getParameter("studentID"));
        String account = req.getParameter("acc");

        req.setAttribute("acc", account);
        req.setAttribute("studentID", studentID);
        req.setAttribute("courseID", courseID);
        req.getRequestDispatcher("selectQuestion.jsp").forward(req, resp);
    }

}

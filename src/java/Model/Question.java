/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.DBContext1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author rinsnow
 */
public class Question {

    String question, answer;
    int courseID;
    int idquestion;

    public Question() {
        connect();

    }

    public Question(String question, String answer, int courseID, int idquestion) {
        this.question = question;
        this.answer = answer;
        this.courseID = courseID;
        this.idquestion = idquestion;
    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question(String question, String answer, int courseID) {
        this.question = question;
        this.answer = answer;
        this.courseID = courseID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "Question{" + "question=" + question + ", answer=" + answer + ", courseID=" + courseID + ", cnn=" + cnn + ", stm=" + stm + ", rs=" + rs + ", pstm=" + pstm + '}';
    }

    Connection cnn;
    Statement stm;
    ResultSet rs;
    PreparedStatement pstm;

    private void connect() {
        try {
            //Sử dụng DBContext1
            cnn = (new DBContext1()).getConnection();
            System.out.println("Connect Success");
        } catch (Exception e) {
            System.out.println("Connect error: " + e.getMessage());
        }
    }

    public ArrayList<Question> getListQuestion(int courseID) {
        ArrayList<Question> list = new ArrayList<Question>();
        try {

            String strSelect = "select * from Question " + "where courseID=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, courseID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String question = rs.getString(1);
                String answer = rs.getString(2);
                Question q = new Question(question, answer);
                list.add(q);
            }
        } catch (Exception e) {
            System.out.println("getlistquestion" + e.getMessage());
        }
        return list;
    }

    public ArrayList<String> getArrayQuestion(int courseID, int lenght) {
        ArrayList<String> list = new ArrayList<>();
        int count = 0;
        try {
            String strSelect = "select Question from Question " + "where courseID=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, courseID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String question = rs.getString(1);
                count++;
                if (count <= lenght) {
                    list.add(question);                   
                }
            }
        } catch (Exception e) {
            System.out.println("getquestion" + e.getMessage());
        }
        return list;
    }

    public ArrayList<String> getArrayAnswer(int courseID, int lenght) {
        ArrayList<String> list = new ArrayList<>();
        int count = 0;
        try {
            String strSelect = "select Answer from Question " + "where courseID=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, courseID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String answer = rs.getString(1);
                count++;
                if (count <= lenght) {
                    list.add(answer);
                }
            }
        } catch (Exception e) {
            System.out.println("getanswer" + e.getMessage());
        }
        return list;
    }

    public double getGrade(Map<String, String> userAnswer, Map<String, String> Answer, int lenght) {
        double grade = 0;
        Map<String, Boolean> result = areEqualKeyValues(userAnswer, Answer);
        for (Map.Entry<String, Boolean> entry : result.entrySet()) {
            if (entry.getValue() == true) {
                grade += (double)10 / lenght;
            }
        }
        return grade;
    }

    private Map<String, Boolean> areEqualKeyValues(Map<String, String> first, Map<String, String> second) {
        return first.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> e.getValue().equals(second.get(e.getKey()))));
    }

    public void saveGrade(int studentID, int courseID, double grade) {
        try {
            connect();
            String strSelect = "Update Grade set Grade=? Where studentID=? AND courseID=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setDouble(1, grade);
            pstm.setInt(2, studentID);
            pstm.setInt(3, courseID);
            pstm.execute();

        } catch (Exception e) {
            System.out.println("update error:" + e.getMessage());
        }
    }

    public double getOldGrade(int studentID, int courseID) {
        double grade = 0;
        try {
            String strSelect = "Select Grade from Grade Where studentID=? AND courseID=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, studentID);
            pstm.setInt(2, courseID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                grade = rs.getDouble(1);
            }
        } catch (Exception e) {
            System.out.println("get grade error" + e.getMessage());
        }
        return grade;
    }

    public ArrayList<Question> getListQuestion1(int courseID, int numberQuestion) {
        ArrayList<Question> list = new ArrayList<Question>();
        int count = 0;
        try {

            String strSelect = "select * from Question " + "where courseID=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, courseID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String question = rs.getString(1);
                String answer = rs.getString(2);
                Question q = new Question(question, answer);
                count++;
                if (count <= numberQuestion) {
                    list.add(q);
                }
            }
        } catch (Exception e) {
            System.out.println("getlistquestion" + e.getMessage());
        }
        return list;
    }

    public void saveDateTime(int studentID, int courseID, String time) {
        try {
            connect();
            String strSelect = "Update Grade set [DateTime]=? Where studentID=? AND courseID=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, time);
            pstm.setInt(2, studentID);
            pstm.setInt(3, courseID);
            pstm.execute();

        } catch (Exception e) {
            System.out.println("update error:" + e.getMessage());
        }
    }
}

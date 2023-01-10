/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.DBContext1;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author rinsnow
 */
public class Student {
    int studentID;
    String name, gender, address;
    Date dob;
    int courseID, Grade;

    public Student() {
        connect();
    }

    public Student(int studentID, String name, String gender, String address, Date dob, int courseID, int Grade) {
        this.studentID = studentID;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.courseID = courseID;
        this.Grade = Grade;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int Grade) {
        this.Grade = Grade;
    }
    
    Connection cnn; //Kết nối DB
    Statement stm; //Thực hiện câu lệnh sql
    ResultSet rs; //Lưu trữ và xử lý dữ liệu
    PreparedStatement pstm;//có thể thực hiện giống thằng stm

    private void connect() {
        try {
            //Sử dụng DBContext1
            cnn = (new DBContext1()).getConnection();
            System.out.println("Connect Success");
        } catch (Exception e) {
            System.out.println("Connect error: " + e.getMessage());
        }
    }
    

   
    
    
}

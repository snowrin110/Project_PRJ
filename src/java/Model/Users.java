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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author rinsnow
 */
public class Users {

    String account, password;
    int studentID;

    public Users(String account, String password) {
        this.account = account;
        this.password = password;
        connect();
    }

    public Users(String account, String password, int studentID) {
        this.account = account;
        this.password = password;
        this.studentID = studentID;
    }

    public Users() {
        connect();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
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

    public boolean checkLogin() {
        try {
            String strSelect = "select * from Users " + "where Account=? and " + "Password=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, account);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return false;
    }

    public int getStudentID(String account) {
        try {
            String strSelect = "select studentID from Users " + "where Account=? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, account);
            rs = pstm.executeQuery();
            while (rs.next()) {                
               studentID = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return studentID;
    }

}

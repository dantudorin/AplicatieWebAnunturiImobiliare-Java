package webapp;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

@javax.servlet.annotation.WebServlet(name = "Login")
public class login extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String action = request.getParameter("action");
        if("Login".equals(action)){
            User user = new User(request.getParameter("username"),request.getParameter("password"));


            try {
                ResultSet resultSet = user.compareData();

                if(resultSet.next()){
                    user.setIdUser(resultSet.getInt("idUser"));
                    request.setAttribute("username",user.getUsername());
                    request.setAttribute("userId",user.getIdUser());
                    request.getRequestDispatcher("/mainApp.jsp").forward(request,response);
                }else{
                    request.setAttribute("errorMessage","Invalid login and password. Try again");
                    request.getRequestDispatcher("/index.jsp").forward(request,response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{

            request.getRequestDispatcher("/registerForm.jsp").forward(request,response);

        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}

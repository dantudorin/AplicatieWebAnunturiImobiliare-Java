package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        if(request.getParameter("firstname").isEmpty() ||
                request.getParameter("lastname").isEmpty() ||
                request.getParameter("username").isEmpty() ||
                request.getParameter("password").isEmpty() ||
                request.getParameter("email").isEmpty()
                ){

                request.setAttribute("errormessage","Please insert in all fields");
                request.getRequestDispatcher("/registerForm.jsp").forward(request,response);

        }else {
                CreateUser createUser = new CreateUser(request.getParameter("firstname"),
                        request.getParameter("lastname"),
                        request.getParameter("username"),
                        request.getParameter("password"),
                        request.getParameter("email")
                );

                if (createUser.exists()) {
                    request.setAttribute("errormessage", "Account already exists. Try again");
                    request.getRequestDispatcher("/registerForm.jsp").forward(request, response);
                } else {
                    createUser.addUser();
                    new MailSender(createUser.getEmail()).sendEmail(0);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

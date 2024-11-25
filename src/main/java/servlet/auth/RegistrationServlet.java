package servlet.auth;

import dto.RegistrationFormDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DataExtractor;

import java.io.IOException;


@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends BaseAuthServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RegistrationFormDto userData = DataExtractor.extractRegistrationData(req);
        if (userService.registerNewUser(userData)) {
            resp.sendRedirect("/signin");
        } else {
            resp.sendError(400, "Account with this email already registered, try to Sign in");
        }
    }
}

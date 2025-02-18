package servlet.auth;

import constant.RouteConstants;
import constant.HtmlConstants;
import dto.RegistrationFormDto;
import exceptions.UserAlreadyExistException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DataExtractor;

import java.io.IOException;


@WebServlet(urlPatterns = RouteConstants.REGISTRATION)
public class RegistrationServlet extends BaseAuthServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(HtmlConstants.REGISTRATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RegistrationFormDto userData = DataExtractor.extractRegistrationData(req);
        try {
            if (userService.registerNewUser(userData)) {
                resp.sendRedirect(RouteConstants.SIGNIN);
            }
        } catch (UserAlreadyExistException e) {
            resp.sendError(400, "Account with email:" + userData.email() + " already registered, try to Sign in");
        }


    }
}

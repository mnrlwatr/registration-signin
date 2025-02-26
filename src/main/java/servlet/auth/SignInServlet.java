package servlet.auth;

import constant.RouteConstants;
import constant.HtmlConstants;
import dto.AuthFormDto;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.DataExtractor;

import java.io.IOException;

@WebServlet(urlPatterns = RouteConstants.SIGNIN)
public class SignInServlet extends BaseAuthServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(HtmlConstants.SIGNIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        AuthFormDto userData = DataExtractor.extractAuthData(req);
        User user = userService.getUserByEmail(userData.email());
        if (user==null) {
            resp.sendError(403, "Account with email:"+ userData.email() +" is not registered");
        } else if (userService.verifyPassword(user, userData.password())) {
            session.setAttribute("UserID", user.getId());
            resp.sendRedirect(RouteConstants.ACCOUNT);
        } else {
            resp.sendError(403, "Incorrect password");
        }
    }
}

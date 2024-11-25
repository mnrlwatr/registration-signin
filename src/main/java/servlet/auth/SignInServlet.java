package servlet.auth;

import dto.AuthFormDto;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.DataExtractor;

import java.io.IOException;

@WebServlet(urlPatterns = "/signin")
public class SignInServlet extends BaseAuthServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signin.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        AuthFormDto userData = DataExtractor.extractAuthData(req);
        User user = userService.findUserByEmail(userData.email());
        if (user==null) {
            resp.sendError(403, "Email is not registered");
        } else if (userService.verifyPassword(user, userData.password())) {
            session.setAttribute("UserID", user.getId());
            resp.sendRedirect("/account");
        } else {
            resp.sendError(403, "Incorrect password");
        }
    }
}

package service;

import dto.AuthFormDto;
import dto.RegistrationFormDto;
import jakarta.servlet.http.HttpServletRequest;

public class DataExtractor {
    public static RegistrationFormDto extractRegistrationData(HttpServletRequest req){
        // TODO на уровне jsp надо сделать валидацию параметров
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        return new RegistrationFormDto(firstname, lastname, email, password);
    }

    public static AuthFormDto extractAuthData(HttpServletRequest req){
        // TODO на уровне jsp надо сделать валидацию параметров
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        return new AuthFormDto(email, password);
    }
}

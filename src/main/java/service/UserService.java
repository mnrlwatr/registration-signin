package service;

import dao.UserDAO;
import dto.RegistrationFormDto;
import entity.User;
import exceptions.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.validator.EmailValidator;

@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDAO userDAO;

    public boolean registerNewUser(RegistrationFormDto userData) throws UserAlreadyExistException {
        if (getUserByEmail(userData.email())!=null){
            throw new UserAlreadyExistException();
        }

        return userDAO.insertUser(User.builder().
                firstname(userData.firstname()).
                lastname(userData.lastname()).
                email(userData.email()).
                password(passwordEncoder.encode(userData.password())).
                build());
    }

    public boolean verifyPassword(User user, String password){
        return passwordEncoder.matches(password, user.getPassword());
    }

    public User getUserByEmail(String email){
        return userDAO.findUserByEmail(email).orElse(null);
    }


}

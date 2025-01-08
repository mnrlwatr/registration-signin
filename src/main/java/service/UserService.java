package service;

import dao.UserDAO;
import dto.RegistrationFormDto;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDAO userDAO;

    public boolean registerNewUser(RegistrationFormDto userData) {
        if (getUserByEmail(userData.email())!=null){
            return false;
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

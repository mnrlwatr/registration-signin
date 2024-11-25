package service;

import dao.UserDAO;
import dto.RegistrationFormDto;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDAO userDAO;

    public boolean registerNewUser(RegistrationFormDto userData) {
        if (findUserByEmail(userData.email())!=null){
            return false;
        }
        return userDAO.addUser(User.builder().
                id(UUID.randomUUID()).
                firstname(userData.firstname()).
                lastname(userData.lastname()).
                email(userData.email()).
                password(passwordEncoder.encode(userData.password())).
                build());
    }

    public User findUserByEmail(String email) {
        return userDAO.getUsersStream().
                filter(user -> user.getEmail().equals(email)).
                findFirst().
                orElse(null);
    }

    public boolean verifyPassword(User user, String password){
        return passwordEncoder.matches(password, user.getPassword());
    }


}

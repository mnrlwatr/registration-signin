package service.validator;

public class PasswordValidator {
    public static boolean validate(String passsword){
        return !passsword.isBlank()&&passsword.length()>7;
    }
}

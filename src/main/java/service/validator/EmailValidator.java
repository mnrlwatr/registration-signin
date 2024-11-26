package service.validator;

import java.util.regex.*;

public class EmailValidator {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern=Pattern.compile(EMAIL_PATTERN);

    public static boolean validate(String email) {
        return pattern.matcher(email).matches();
    }
}
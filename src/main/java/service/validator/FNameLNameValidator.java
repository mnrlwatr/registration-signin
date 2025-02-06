package service.validator;

public class FNameLNameValidator {
    public static boolean validate(String name ){
        return !name.isBlank();
    }
}

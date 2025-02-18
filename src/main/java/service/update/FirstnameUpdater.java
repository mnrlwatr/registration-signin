package service.update;
import entity.User;
import exceptions.invalidData.InvalidFirstnameException;
import service.validator.FNameLNameValidator;

public class FirstnameUpdater extends AbstractUserUpdater {

    private final String newFirstname;

    public FirstnameUpdater(String newFirstname) throws InvalidFirstnameException {
        if(!FNameLNameValidator.validate(newFirstname)){
            throw new InvalidFirstnameException("Invalid firstname");
        }
        this.newFirstname = newFirstname;
    }

    @Override
    public boolean executeUpdate(User userToBeUpdated) {
        userToBeUpdated.setFirstname(newFirstname);
        return true;
    }

}

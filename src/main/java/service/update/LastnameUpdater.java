package service.update;

import entity.User;
import exceptions.invalidData.InvalidLastnameException;
import service.validator.FNameLNameValidator;

public class LastnameUpdater extends AbstractUserUpdater {
    private final String newLastname;

    public LastnameUpdater(String newLastname) throws InvalidLastnameException {
        if(!FNameLNameValidator.validate(newLastname)){
            throw new InvalidLastnameException("Invalid lastname");
        }
        this.newLastname = newLastname;
    }

    @Override
    public boolean executeUpdate(User userToBeUpdated) {
        userToBeUpdated.setLastname(newLastname);
        return true;
    }

}

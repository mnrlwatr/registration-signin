package service.update;

import entity.User;

public abstract class AbstractUserUpdater {

    public abstract boolean executeUpdate (User userToBeUpdated);

}

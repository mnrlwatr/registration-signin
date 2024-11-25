package dao;

import entity.User;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

public class UserDAO {

    private static final Set<User> usersSet = new HashSet<>();

    public boolean addUser (User user) {
        return usersSet.add(user);
    }

    public boolean deleteUser (User user){
        return usersSet.remove(user);
    }

    public Stream<User> getUsersStream (){
        return usersSet.stream();
    }

    public Iterator<User> getUsersIterator (){
        return usersSet.iterator();
    }
}

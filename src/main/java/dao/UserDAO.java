package dao;

import entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserDAO {

    private final SessionFactory sessionFactory;

    public boolean insertUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return true;
        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }

    public Optional<User> findUserByEmail(String email) {
        User user =null;
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            user=query.uniqueResult();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return Optional.ofNullable(user);
    }
    public Optional<User> findUserById(UUID id) {
        User user =null;
        try (Session session = sessionFactory.openSession()) {
            user=session.find(User.class,id);
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return Optional.ofNullable(user);
    }


}

package dao;

import entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.*;
import org.hibernate.query.MutationQuery;
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
            throw new RuntimeException(he);
        }
        return Optional.ofNullable(user);
    }
    public Optional<User> findUserById(UUID id) {
        User user =null;
        try (Session session = sessionFactory.openSession()) {
            user=session.find(User.class,id);
        } catch (HibernateException he) {
            throw new RuntimeException(he);
        }
        return Optional.ofNullable(user);
    }

    public boolean deleteUserById(UUID id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            MutationQuery mutationQuery = session.createMutationQuery("DELETE FROM User u WHERE id = :id");
            mutationQuery.setParameter("id", id);
            mutationQuery.executeUpdate();
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }

    public boolean deleteUser(User user) {
        Transaction transaction=null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
            return true;
        }
        catch(HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }

    public boolean updateUser (User user) {
        Transaction transaction=null;
        try (Session session = sessionFactory.openSession()) {
            transaction=session.beginTransaction();
            session.merge(user);
            transaction.commit();
            return true;
        } catch(HibernateException he) {
            if(transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }

    public User getReference (UUID id){
        try (Session session = sessionFactory.openSession()) {
          return session.getReference(User.class,id);
        } catch(HibernateException he) {
            throw new RuntimeException(he);
        }
    }

}

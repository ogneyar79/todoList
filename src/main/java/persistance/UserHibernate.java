package persistance;

import model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import persistance.workwithbase.ICRUD;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserHibernate implements ICRUD<Users> {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final UserHibernate INST = new UserHibernate();
    }

    public static UserHibernate instOf() {
        return UserHibernate.Lazy.INST;
    }

    @Override
    public Users add(Users user) throws SQLException {
        Serializable id = this.makeTransaction(session -> session.save(user));
        //  return (Users) makeTransaction(new FJC(user));
        return makeTransaction(s -> s.get(Users.class, id));
    }

    @Override
    public Users getById(int id) {
        return makeTransaction(s -> s.get(Users.class, id));
    }

    @Override
    public List<Users> findAll() throws SQLException {
        Users userNULL = new Users(0L, "ZERO", "mail", "0000");
        List<Users> empty = new ArrayList<>();
        empty.add(userNULL);
        List<Users> result = makeTransaction(session -> session.createQuery("from Users ").list());
        return result.size() == 0 ? empty : result;
    }

    @Override
    public Users delete(int id) {
        Users user = getById(id);
        return makeTransaction(session -> {
            session.delete(user);
            return user;
        });
    }

    @Override
    public Users update(int id, Users user) throws SQLException {
        return this.makeTransaction(session -> {
            session.update(user);
            return getById(id);
        });
    }

    // default method ? hire
    @Override
    public <T> T makeTransaction(Function<Session, T> operationCRUID) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        T result = operationCRUID.apply(session);
        transaction.commit();
        session.close();
        return result;
    }

    public static void main(String... args) throws SQLException {
        UserHibernate.instOf().add(new Users("Maksimus", "maksimus.s@mail.ru", "Ma2444"));

    }
}

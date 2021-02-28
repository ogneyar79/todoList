package persistance;

import model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jboss.logging.Logger;
import persistance.workwithbase.ICRUD;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserHibernate implements ICRUD<Users> {
    private static Logger LOG = Logger.getLogger(UserHibernate.class.getName());
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
        LOG.info("String 36 AddUser");
        LOG.info(" 37 AddUser" + user.getName() + ":" + user.toString());
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

    public Users findByEmail(String email) {
        Users result = (Users) this.makeTransaction(session -> session.createQuery("from Users where email='" + email + "'").getSingleResult());
        return result == null ? new Users("Zero", "Zero", "0000") : result;
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
        LOG.info("Begin method Main");
        UserHibernate.instOf().add(new Users("Maksimus", "maksimus.s@mail.ru", "Ma2444"));
    }
}

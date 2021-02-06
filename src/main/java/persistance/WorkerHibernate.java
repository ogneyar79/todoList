package persistance;


import model.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jboss.logging.Logger;
import persistance.workwithbase.ICRUD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class WorkerHibernate implements ICRUD<Task>, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final WorkerHibernate INST = new WorkerHibernate();
    }

    public static WorkerHibernate instOf() {
        return Lazy.INST;
    }

    private static Logger LOG = Logger.getLogger(WorkerHibernate.class.getName());

    @Override
    public Task add(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        Serializable id = session.save(task);
        session.getTransaction().commit();
        Task it = session.get(Task.class, id);
        session.close();
        return it;
    }

    @Override
    public Task getById(int id) {
        return makeTransaction(session -> session.get(Task.class, id));
    }

    @Override
    public List<Task> findAll() {
        System.out.println(" FindAllWorkerHibernate  + 49");
        Task emptyTask = new Task();
        emptyTask.setId(0);

        emptyTask.setDescription("EMPTY TASK");
        List<Task> empty = new ArrayList<>();
        empty.add(emptyTask);
        List<Task> result = this.makeTransaction(session -> session.createQuery("from Task ").list());
        System.out.println(result.size() + ": Size of result");
        LOG.info(result.size()+"INFO LOG");
        result.stream().forEach(System.out::println);
        result.stream().map(task -> {
            LOG.info(task.toString()+"INFO LOG");
            return task;
        });
        return result.size() == 0 ? empty : result;
    }

    @Override
    public Task delete(int id) {
        Task task = this.getById(id);
        return makeTransaction(session -> {
            session.delete(String.valueOf(id), task);
            return task;
        });
    }

    @Override
    public Task update(int id, Task task) {
        return this.makeTransaction(session -> {
            session.update(String.valueOf(id), task);
            return getById(id);
        });
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    public <T> T makeTransaction(final Function<Session, T> operationCRUID) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        T result = operationCRUID.apply(session);
        transaction.commit();
        session.close();
        return result;
    }

    public void deleteAll() {
        makeTransaction(session -> session.createQuery("delete from  Task ").executeUpdate());
    }

}

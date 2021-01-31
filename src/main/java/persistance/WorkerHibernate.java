package persistance;

import model.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import persistance.workwithbase.ICRUD;

import java.sql.Timestamp;
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

    @Override
    public Task add(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
        session.close();
        Task it = task;
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
        //       emptyTask.setCreated(new Timestamp(System.currentTimeMillis()));
        emptyTask.setDescription("EMPTY TASK");
        //       emptyTask.setDone(false);
        List<Task> empty = new ArrayList<>();
        empty.add(emptyTask);
        List<Task> result = this.makeTransaction(session -> session.createQuery("from Task ").list());
        System.out.println(result.size() + ": Size of result");
        result.stream().forEach(System.out::println);
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
}

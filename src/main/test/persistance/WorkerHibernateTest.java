package persistance;

import model.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class WorkerHibernateTest {

    Task taskOne;
    Task taskTwo;
    Task taskThree;
    List<Task> taskList = new ArrayList<>();
    WorkerHibernate workerHibernate;
    String strOne;

    @Before
    public void install() {
        workerHibernate = WorkerHibernate.instOf();
        workerHibernate.deleteAll();

        taskTwo = new Task();
        taskThree = new Task();
        strOne = "Test one";
    }

    @Test
    public void testAdd() {
        taskList = workerHibernate.findAll();
        taskList.stream().forEach(System.out::println);
        taskOne = new Task();
        taskOne.setDescription(strOne);
        Task test = workerHibernate.add(taskOne);
        System.out.println(test + ": Task Test, 40 TestAdd");
        workerHibernate.findAll().stream().forEach(System.out::println);
        assertThat(test.getDescription(), is(strOne));
    }

    public void testGetById() {
    }

    public void testFindAll() {
    }

    public void testDelete() {
    }

    public void testUpdate() {
    }
}
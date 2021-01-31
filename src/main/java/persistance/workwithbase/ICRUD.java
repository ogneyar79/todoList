package persistance.workwithbase;

import model.Task;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


public interface ICRUD<T> {

    T add(T task) throws SQLException;

    T getById(int id);

    List<T> findAll() throws SQLException;

    T delete(int id);

    T update(int id, T task) throws SQLException;
}

package persistance.workwithbase;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;


public interface ICRUD<T> {

    T add(T task) throws SQLException;

    T getById(int id);

    List<T> findAll() throws SQLException;

    T delete(int id);

    T update(int id, T task) throws SQLException;
    <T> T makeTransaction(final Function<Session, T> operationCRUID);

}

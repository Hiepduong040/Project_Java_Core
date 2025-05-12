package ra.edu.business.dao;

import java.util.List;

public interface AppDAO<T> {
    List<T> findAll();

    boolean save(T t);

    boolean update(T t);

    boolean delete(T t);
}

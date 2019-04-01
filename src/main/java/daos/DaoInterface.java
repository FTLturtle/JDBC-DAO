package daos;

import java.util.List;

public interface DaoInterface<T> {
    T findById(Long id);
    List<T> findAll();
    Boolean update(T dto);
    Boolean create(T dto);
    Boolean delete(Long id);
}

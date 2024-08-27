package sorveteria.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICrudService<T, ID> {
    T create(T entity);

    Page<T> listAll(Pageable pageable);

    T listById(ID id);

    void deleteById(ID id);

    T update(T entity, ID id);
}

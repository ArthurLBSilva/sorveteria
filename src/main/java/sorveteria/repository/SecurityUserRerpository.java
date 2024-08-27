package sorveteria.repository;

import org.springframework.data.repository.CrudRepository;
import sorveteria.model.SecurityUser;

public interface SecurityUserRerpository extends CrudRepository<SecurityUser, Long> {
    SecurityUser findByUsername(String username);
}

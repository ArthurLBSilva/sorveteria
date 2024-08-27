package sorveteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sorveteria.model.Sabor;

@Repository
public interface SaborRepository extends JpaRepository<Sabor, Long> {
}

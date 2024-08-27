package sorveteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sorveteria.model.Calda;

@Repository
public interface CaldaRepository extends JpaRepository<Calda, Long> {
}
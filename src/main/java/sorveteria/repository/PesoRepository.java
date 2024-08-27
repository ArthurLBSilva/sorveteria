package sorveteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sorveteria.model.Peso;

public interface PesoRepository extends JpaRepository<Peso, Long> {
}

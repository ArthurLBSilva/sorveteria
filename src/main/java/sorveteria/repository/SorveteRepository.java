package sorveteria.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sorveteria.model.Sorvete;

public interface SorveteRepository extends JpaRepository<Sorvete, Long> {
}

package sorveteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sorveteria.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}

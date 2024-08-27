package sorveteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sorveteria.model.Carrinho;
import sorveteria.model.Usuario;

import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Optional<Carrinho> findByUsuario(Usuario usuario);
}

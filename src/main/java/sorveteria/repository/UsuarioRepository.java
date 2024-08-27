package sorveteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sorveteria.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailAndSenha(String email, String senha);
}

package sorveteria.service;

import org.springframework.stereotype.Service;
import sorveteria.model.Usuario;
import sorveteria.repository.UsuarioRepository;
import sorveteria.service.generic.GenericCrudService;

@Service
public class UsuarioService extends GenericCrudService<Usuario, Long, UsuarioRepository> {

    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }

    public Usuario findByEmailAndSenha(String email, String senha) {
        return repository.findByEmailAndSenha(email, senha)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com as credenciais fornecidas"));
    }
}

package sorveteria.service;

import org.springframework.stereotype.Service;
import sorveteria.model.Carrinho;
import sorveteria.model.Usuario;
import sorveteria.repository.CarrinhoRepository;
import sorveteria.service.generic.GenericCrudService;

import java.util.Optional;

@Service
public class CarrinhoService extends GenericCrudService<Carrinho, Long, CarrinhoRepository> {

    public CarrinhoService(CarrinhoRepository repository) {
        super(repository);
    }

    public Carrinho findByUsuario(Usuario usuario) {
        Optional<Carrinho> carrinho = repository.findByUsuario(usuario);
        return carrinho.orElseThrow(() -> new RuntimeException("Carrinho não encontrado para o usuário: " + usuario.getId()));
    }

    public Carrinho findByUsuarioId(Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId); 
        return findByUsuario(usuario);
    }
}


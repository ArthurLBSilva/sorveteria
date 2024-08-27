package sorveteria.service;

import org.springframework.stereotype.Service;
import sorveteria.model.Endereco;
import sorveteria.repository.EnderecoRepository;
import sorveteria.service.generic.GenericCrudService;

@Service
public class EnderecoService extends GenericCrudService<Endereco, Long, EnderecoRepository> {

    public EnderecoService(EnderecoRepository repository) {
        super(repository);
    }

}

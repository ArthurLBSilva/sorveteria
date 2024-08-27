package sorveteria.service;

import org.springframework.stereotype.Service;
import sorveteria.model.Peso;
import sorveteria.repository.PesoRepository;
import sorveteria.service.generic.GenericCrudService;

@Service
public class PesoService extends GenericCrudService<Peso, Long, PesoRepository> {

    public PesoService(PesoRepository repository) {
        super(repository);
    }
}

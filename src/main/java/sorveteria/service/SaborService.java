package sorveteria.service;

import org.springframework.stereotype.Service;
import sorveteria.model.Sabor;
import sorveteria.repository.SaborRepository;
import sorveteria.service.generic.GenericCrudService;

@Service
public class SaborService extends GenericCrudService<Sabor, Long, SaborRepository> {
    public SaborService(SaborRepository repository) {
        super(repository);
    }
}

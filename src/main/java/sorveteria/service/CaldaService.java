package sorveteria.service;

import org.springframework.stereotype.Service;
import sorveteria.model.Calda;
import sorveteria.repository.CaldaRepository;
import sorveteria.service.generic.GenericCrudService;

@Service
public class CaldaService extends GenericCrudService<Calda, Long, CaldaRepository> {

    public CaldaService(CaldaRepository repository) {
        super(repository);
    }
}
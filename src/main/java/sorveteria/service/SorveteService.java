package sorveteria.service;



import org.springframework.stereotype.Service;
import sorveteria.model.Carrinho;
import sorveteria.model.Sorvete;
import sorveteria.repository.SorveteRepository;
import sorveteria.service.generic.GenericCrudService;

@Service
public class SorveteService extends GenericCrudService<Sorvete, Long, SorveteRepository> {

    public SorveteService(SorveteRepository repository) {
        super(repository);
    }

    public void removeFromCarrinho(Carrinho carrinho, Long sorveteId) {
        Sorvete sorvete = repository.findById(sorveteId)
                .orElseThrow(() -> new RuntimeException("Sorvete não encontrado"));
        
        if (sorvete.getCarrinho().equals(carrinho)) {
            carrinho.getSorvetes().remove(sorvete);
            sorvete.setCarrinho(null);
            repository.save(sorvete);
        } else {
            throw new RuntimeException("Sorvete não pertence ao carrinho especificado");
        }
    }

    
    
}

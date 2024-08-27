package sorveteria.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sorveteria.dto.DtoSorvete.SorveteRequestDto;
import sorveteria.dto.DtoSorvete.SorveteResponseDto;
import sorveteria.model.Sorvete;
import sorveteria.model.Peso;
import sorveteria.model.Sabor;
import sorveteria.model.Calda;
import sorveteria.model.Carrinho;
import sorveteria.service.SorveteService;
import sorveteria.service.PesoService;
import sorveteria.service.SaborService;
import sorveteria.service.CaldaService;
import sorveteria.service.CarrinhoService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sorvetes/")
@AllArgsConstructor
public class SorveteController {

    private final SorveteService sorveteService;
    private final PesoService pesoService;
    private final SaborService saborService;
    private final CaldaService caldaService;
    private final CarrinhoService carrinhoService;
    private final ModelMapper mapper;

    @GetMapping
    public Page<SorveteResponseDto> listAll(Pageable pageable) {
        Page<Sorvete> sorvetesPage = sorveteService.listAll(pageable);
        return sorvetesPage.map(this::convertToDto);
    }

    @PostMapping("/{usuarioId}")
    public ResponseEntity<SorveteResponseDto> create(@PathVariable("usuarioId") Long usuarioId, 
                                                     @RequestBody SorveteRequestDto sorveteRequestDto) {

        Carrinho carrinho = carrinhoService.findByUsuarioId(usuarioId);
        
        Sorvete sorveteToCreate = convertToEntity(sorveteRequestDto);

        sorveteToCreate.setCarrinho(carrinho);

        Peso peso = pesoService.listById(sorveteRequestDto.getPesoId());
        sorveteToCreate.setPreco(peso.getValor());

        Sorvete createdSorvete = sorveteService.create(sorveteToCreate);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdSorvete.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(createdSorvete));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SorveteResponseDto> listById(@PathVariable("id") Long id) {
        Sorvete sorvete = sorveteService.listById(id);
        return ResponseEntity.ok(convertToDto(sorvete));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SorveteResponseDto> update(@RequestBody SorveteRequestDto sorveteRequestDto, @PathVariable("id") Long id) {
        Sorvete sorveteToUpdate = convertToEntity(sorveteRequestDto);
        sorveteToUpdate.setId(id);

        Sorvete updatedSorvete = sorveteService.update(sorveteToUpdate, id);
        return ResponseEntity.ok(convertToDto(updatedSorvete));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        sorveteService.deleteById(id);
    }

    private SorveteResponseDto convertToDto(Sorvete sorvete) {
        return mapper.map(sorvete, SorveteResponseDto.class);
    }

    private Sorvete convertToEntity(SorveteRequestDto sorveteRequestDto) {
        Sorvete sorvete = new Sorvete();

        Peso peso = pesoService.listById(sorveteRequestDto.getPesoId());
        sorvete.setPeso(peso);
        sorvete.setPreco(peso.getValor()); 

        List<Sabor> sabores = sorveteRequestDto.getSaborIds().stream()
                .map(saborService::listById)
                .collect(Collectors.toList());
        sorvete.setSabores(sabores);

        Calda calda = caldaService.listById(sorveteRequestDto.getCaldaId());
        sorvete.setCalda(calda);
        
        return sorvete;
    }
}

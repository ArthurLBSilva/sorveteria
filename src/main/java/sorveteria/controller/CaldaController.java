package sorveteria.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sorveteria.dto.DtoCalda.CaldaRequestDto;
import sorveteria.dto.DtoCalda.CaldaResponseDto;
import sorveteria.model.Calda;
import sorveteria.service.CaldaService;

import java.net.URI;

@RestController
@RequestMapping("/caldas")
@AllArgsConstructor
public class CaldaController {

    private final CaldaService service;
    private final ModelMapper mapper;

    @GetMapping
    public Page<CaldaResponseDto> listAll(Pageable pageable) {
        Page<Calda> caldasPage = service.listAll(pageable);
        return caldasPage.map(this::convertToDto);
    }

    @PostMapping
    public ResponseEntity<CaldaResponseDto> create(@RequestBody CaldaRequestDto caldaRequestDto) {
        // Se estoque for null, define como "Disponível"
        String estoque = caldaRequestDto.getEstoque();
        if (estoque == null) {
            estoque = "Disponível";
        }

        // Cria uma nova entidade Calda com o estoque definido
        Calda calda = convertToEntity(caldaRequestDto);
        calda.setEstoque(estoque);

        // Salva a nova entidade Calda
        Calda created = service.create(calda);

        // Define a URI do novo recurso
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        // Retorna a resposta com o DTO criado
        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaldaResponseDto> listById(@PathVariable("id") Long id) {
        Calda calda = service.listById(id);
        CaldaResponseDto dto = convertToDto(calda);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaldaResponseDto> update(@RequestBody CaldaRequestDto requestDto, @PathVariable("id") Long id) {
        try {
            // Verifica se a entidade existe
            Calda existingCalda = service.listById(id);

            // Atualiza os campos da entidade existente com os dados do DTO
            existingCalda.setNome(requestDto.getNome());

            // Verifica se o estoque foi fornecido no DTO e se é não-nulo
            if (requestDto.getEstoque() == null || requestDto.getEstoque().isBlank()) {
                return ResponseEntity.badRequest().body(null); // Ou uma mensagem de erro apropriada
            }
            existingCalda.setEstoque(requestDto.getEstoque());

            // Atualiza a entidade no banco de dados
            Calda updatedCalda = service.update(existingCalda, id);

            // Retorna a resposta com o DTO atualizado
            return ResponseEntity.ok(convertToDto(updatedCalda));

        } catch (RuntimeException e) {
            // Se a entidade não existir, retorna um erro 404
            return ResponseEntity.notFound().build();
        }
    }

    private CaldaResponseDto convertToDto(Calda calda) {
        return mapper.map(calda, CaldaResponseDto.class);
    }

    private Calda convertToEntity(CaldaRequestDto dto) {
        return mapper.map(dto, Calda.class);
    }
}
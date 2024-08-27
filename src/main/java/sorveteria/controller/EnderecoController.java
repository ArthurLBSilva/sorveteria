package sorveteria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sorveteria.dto.DtoEndereço.EnderecoRequestDto;
import sorveteria.dto.DtoEndereço.EnderecoResponseDto;
import sorveteria.model.Endereco;
import sorveteria.service.EnderecoService;



@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDto> criarEndereco(@RequestBody EnderecoRequestDto enderecoRequestDto) {
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoRequestDto.getRua());
        endereco.setNumero(enderecoRequestDto.getNumero());

        Endereco novoEndereco = enderecoService.create(endereco);
        EnderecoResponseDto responseDto = new EnderecoResponseDto(
                novoEndereco.getId(),
                novoEndereco.getRua(),
                novoEndereco.getNumero()
        );

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<EnderecoResponseDto>> listarEnderecos(Pageable pageable) {
        Page<Endereco> enderecosPage = enderecoService.listAll(pageable);
        Page<EnderecoResponseDto> responseDtos = enderecosPage.map(endereco ->
                new EnderecoResponseDto(
                        endereco.getId(),
                        endereco.getRua(),
                        endereco.getNumero()
                )
        );
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> obterEnderecoPorId(@PathVariable Long id) {
        Endereco endereco = enderecoService.listById(id);
        EnderecoResponseDto responseDto = new EnderecoResponseDto(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero()
        );
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoRequestDto enderecoRequestDto) {
        Endereco endereco = enderecoService.listById(id);
        endereco.setRua(enderecoRequestDto.getRua());
        endereco.setNumero(enderecoRequestDto.getNumero());

        Endereco enderecoAtualizado = enderecoService.update(endereco, id);
        EnderecoResponseDto responseDto = new EnderecoResponseDto(
                enderecoAtualizado.getId(),
                enderecoAtualizado.getRua(),
                enderecoAtualizado.getNumero()
        );

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable Long id) {
        enderecoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

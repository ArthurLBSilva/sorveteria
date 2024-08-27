package sorveteria.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sorveteria.dto.DtoCarrinho.CarrinhoResponseDto;
import sorveteria.dto.DtoSorvete.SorveteRequestDto;
import sorveteria.dto.DtoSorvete.SorveteResponseDto;
import sorveteria.model.Carrinho;
import sorveteria.model.Sorvete;
import sorveteria.model.Usuario;
import sorveteria.service.CarrinhoService;
import sorveteria.service.SorveteService;
import sorveteria.service.UsuarioService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carrinhos")
@AllArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;
    private final SorveteService sorveteService;
    private final UsuarioService usuarioService;
    private final ModelMapper mapper;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<CarrinhoResponseDto> getCarrinhoByUsuario(@PathVariable("usuarioId") Long usuarioId) {
        Usuario usuario = usuarioService.listById(usuarioId);
        Carrinho carrinho = carrinhoService.findByUsuario(usuario);
        return ResponseEntity.ok(convertToDto(carrinho));
    }

    @PostMapping("/{usuarioId}")
    public ResponseEntity<CarrinhoResponseDto> createCarrinho(@PathVariable("usuarioId") Long usuarioId) {
        Usuario usuario = usuarioService.listById(usuarioId);
        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(usuario);

        Carrinho createdCarrinho = carrinhoService.create(carrinho);

        URI location = URI.create("/carrinhos/" + createdCarrinho.getId());
        return ResponseEntity.created(location).body(convertToDto(createdCarrinho)); 
    }

    @PostMapping("/{usuarioId}/sorvetes")
    public ResponseEntity<SorveteResponseDto> addSorveteToCarrinho(@PathVariable("usuarioId") Long usuarioId,
                                                                   @RequestBody SorveteRequestDto sorveteRequestDto) {
        Usuario usuario = usuarioService.listById(usuarioId);
        Carrinho carrinho = carrinhoService.findByUsuario(usuario);

        Sorvete sorvete = convertToEntity(sorveteRequestDto);
        sorvete.setCarrinho(carrinho);

        Sorvete createdSorvete = sorveteService.create(sorvete);

        URI location = URI.create("/carrinhos/" + usuarioId + "/sorvetes/" + createdSorvete.getId());
        return ResponseEntity.created(location).body(convertToDto(createdSorvete));
    }

    @DeleteMapping("/{usuarioId}/sorvetes/{sorveteId}")
    public ResponseEntity<Void> removeSorveteFromCarrinho(@PathVariable("usuarioId") Long usuarioId,
                                                          @PathVariable("sorveteId") Long sorveteId) {
        Usuario usuario = usuarioService.listById(usuarioId);
        Carrinho carrinho = carrinhoService.findByUsuario(usuario);

        Sorvete sorvete = sorveteService.listById(sorveteId);

        carrinho.getSorvetes().remove(sorvete);
        sorveteService.deleteById(sorveteId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{usuarioId}/sorvetes")
    public ResponseEntity<List<SorveteResponseDto>> listSorvetesByCarrinho(@PathVariable("usuarioId") Long usuarioId) {
        Usuario usuario = usuarioService.listById(usuarioId);
        Carrinho carrinho = carrinhoService.findByUsuario(usuario);

        List<SorveteResponseDto> sorvetes = carrinho.getSorvetes().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(sorvetes);
    }

    private CarrinhoResponseDto convertToDto(Carrinho carrinho) {
        CarrinhoResponseDto dto = mapper.map(carrinho, CarrinhoResponseDto.class);
        dto.setSorvetes(carrinho.getSorvetes().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
        return dto;
    }    

    private SorveteResponseDto convertToDto(Sorvete sorvete) {
        return mapper.map(sorvete, SorveteResponseDto.class);
    }

    private Sorvete convertToEntity(SorveteRequestDto sorveteRequestDto) {
        return mapper.map(sorveteRequestDto, Sorvete.class);
    }
}

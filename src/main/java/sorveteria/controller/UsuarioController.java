package sorveteria.controller;

import org.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sorveteria.dto.DtoCarrinho.CarrinhoResponseDto;
import sorveteria.dto.DtoEndereço.EnderecoResponseDto;
import sorveteria.dto.DtoUsuario.UsuarioRequestDto;
import sorveteria.dto.DtoUsuario.UsuarioResponseDto;
import sorveteria.model.Endereco;
import sorveteria.model.Usuario;
import sorveteria.service.EnderecoService;
import sorveteria.service.UsuarioService;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EnderecoService enderecoService;
    private final ModelMapper mapper;

    @GetMapping
    public Page<UsuarioResponseDto> listAll(Pageable pageable) {
        Page<Usuario> usuariosPage = usuarioService.listAll(pageable);
        return usuariosPage.map(this::convertToDto);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        if (usuarioRequestDto.getEnderecoId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Endereco endereco = enderecoService.listById(usuarioRequestDto.getEnderecoId());
        Usuario usuario = convertToEntity(usuarioRequestDto);
        usuario.setEndereco(endereco);

        Usuario createdUsuario = usuarioService.create(usuario);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUsuario.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(createdUsuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> listById(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.listById(id);
        return ResponseEntity.ok(convertToDto(usuario));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        usuarioService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> update(@RequestBody UsuarioRequestDto usuarioRequestDto, @PathVariable("id") Long id) {
        if (usuarioRequestDto.getEnderecoId() == null) {
            return ResponseEntity.badRequest().build();
        }
    
        Endereco endereco = enderecoService.listById(usuarioRequestDto.getEnderecoId());
    
        Usuario existingUsuario = usuarioService.listById(id);
    
        existingUsuario.setNome(usuarioRequestDto.getNome());
        existingUsuario.setEmail(usuarioRequestDto.getEmail());
        existingUsuario.setSenha(usuarioRequestDto.getSenha());
        existingUsuario.setEndereco(endereco);
    
        Usuario updatedUsuario = usuarioService.update(existingUsuario, id);
    
        return ResponseEntity.ok(convertToDto(updatedUsuario));
    }
    

    private UsuarioResponseDto convertToDto(Usuario usuario) {
        UsuarioResponseDto dto = mapper.map(usuario, UsuarioResponseDto.class);
        dto.setEndereco(mapper.map(usuario.getEndereco(), EnderecoResponseDto.class));
        
        if (usuario.getCarrinho() != null) {
            dto.setCarrinho(mapper.map(usuario.getCarrinho(), CarrinhoResponseDto.class));
        }
        
        return dto;
    }

    private Usuario convertToEntity(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = mapper.map(usuarioRequestDto, Usuario.class);
    
        Endereco endereco = enderecoService.listById(usuarioRequestDto.getEnderecoId());
        usuario.setEndereco(endereco);
    
        return usuario;
    }

        @GetMapping("/login")
        public ResponseEntity<UsuarioResponseDto> login(@RequestParam("email") String email,
        @RequestParam("senha") String senha) {
        Usuario usuario = usuarioService.findByEmailAndSenha(email, senha);
        return ResponseEntity.ok(convertToDto(usuario));
    }
    
}

package sorveteria.dto.DtoCarrinho;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sorveteria.dto.DtoSorvete.SorveteResponseDto;
import sorveteria.dto.DtoUsuario.UsuarioResponseDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoResponseDto {

    Long id;
    UsuarioResponseDto usuario;
    private List<SorveteResponseDto> sorvetes;
}

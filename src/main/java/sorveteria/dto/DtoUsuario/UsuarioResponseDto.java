package sorveteria.dto.DtoUsuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sorveteria.dto.DtoCarrinho.CarrinhoResponseDto;
import sorveteria.dto.DtoEndereço.EnderecoResponseDto;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDto {

    Long id;
    String nome;
    String email;
    EnderecoResponseDto endereco;
    CarrinhoResponseDto carrinho;
}

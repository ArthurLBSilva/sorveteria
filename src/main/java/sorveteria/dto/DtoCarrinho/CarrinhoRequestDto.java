package sorveteria.dto.DtoCarrinho;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoRequestDto {

    @NotNull(message = "O ID do usuário não pode ser nulo.")
    Long usuarioId;

    List<Long> sorvetes;
}
